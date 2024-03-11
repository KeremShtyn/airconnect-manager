package com.chainerist.blockchain.manager.airconnect.controller;

import com.chainerist.blockchain.manager.airconnect.api.PassengerAPI;
import com.chainerist.blockchain.manager.airconnect.builder.SpecificationBuilder;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Passenger;
import com.chainerist.blockchain.manager.airconnect.dto.PassengerDTO;
import com.chainerist.blockchain.manager.airconnect.dto.SearchDTO;
import com.chainerist.blockchain.manager.airconnect.mapper.PassengerDTOMapper;
import com.chainerist.blockchain.manager.airconnect.service.PassengerService;
import com.chainerist.blockchain.manager.airconnect.util.SearchCriteria;
import com.chainerist.blockchain.manager.util.response.ChaineristGenerator;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Slf4j
public class PassengerController implements PassengerAPI {

    private PassengerService passengerService;
    private PassengerDTOMapper passengerDTOMapper;
    public static final String SEARCHCRITERIALIST = "searchcriterialist";
    private ObjectMapper objectMapper;

    public PassengerController(PassengerService passengerService, PassengerDTOMapper passengerDTOMapper, ObjectMapper objectMapper) {
        this.passengerService = passengerService;
        this.passengerDTOMapper = passengerDTOMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public ChaineristResponse<Object> save(PassengerDTO passengerDTO) {
        Passenger domainObject = passengerDTOMapper.toDomain(passengerDTO);
        domainObject = passengerService.createPassenger(domainObject);
        return ChaineristGenerator.generateSignResponse(passengerDTOMapper.toDTO(domainObject));
    }

    @Override
    public ChaineristResponse<Object> update(PassengerDTO passengerDTO) {
        return save(passengerDTO);
    }

    @Override
    public ChaineristResponse<Object> getById(String id) {
        Passenger ps = passengerService.findById(id);
        return ChaineristGenerator.generateSignResponse(passengerDTOMapper.toDTO(ps));
    }

    @Override
    public ChaineristResponse<Object> getPage(Map<String, Object> header, int page, int size, String[] sortBy) {
        ChaineristPageable<Passenger> passengerPage = passengerService.findAll(page, size);
        return ChaineristGenerator.generateSignResponse(passengerPage);
    }

    @Override
    public ChaineristResponse<Object> getByFirstName(String firstName) {
        List<Passenger> ps = passengerService.findByFirstName(firstName);
        return ChaineristGenerator.generateSignResponse(passengerDTOMapper.toListDTO(ps));
    }

    @Override
    public ChaineristResponse<Object> searchPassenger(Map<String, Object> header, int pageNum, int pageSize) {
        List<SearchCriteria> criteriaList = convertHeader(header);

        SearchDTO searchDTO = objectMapper.convertValue(header, SearchDTO.class);
        searchDTO.setSearchCriteriaList(criteriaList);

        SpecificationBuilder builder = new SpecificationBuilder();
        criteriaList = searchDTO.getSearchCriteriaList();
        if (criteriaList != null) {
            criteriaList.forEach(x -> {
                x.setDataOption(searchDTO.getDataOption());
                builder.with(x);
            });

        }

        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("id").ascending());
        ChaineristPageable<Passenger> passengerPage = passengerService.findBySearchCriteria(builder.buildPassenger(), page);
        passengerPage = new ChaineristPageable<>(passengerPage.getTotalElements(), passengerPage.getTotalPages(), passengerPage.getPageable(), passengerPage.getContents());


        return ChaineristGenerator.generateSignResponse(passengerPage);
    }

    private List<SearchCriteria> convertHeader(Map<String, Object> header) {
        String searchCriteriaList = (String) header.get(SEARCHCRITERIALIST);
        if (StringUtils.isEmpty(searchCriteriaList)) {
            return List.of();
        }

        SearchCriteria[] searchCriterias = null;
        try {
            searchCriterias = objectMapper.convertValue(new JSONParser(searchCriteriaList).parse(), SearchCriteria[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        header.remove(SEARCHCRITERIALIST);
        return Arrays.asList(searchCriterias);
    }
}
