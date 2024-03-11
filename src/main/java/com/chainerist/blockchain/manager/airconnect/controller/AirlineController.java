package com.chainerist.blockchain.manager.airconnect.controller;

import com.chainerist.blockchain.manager.airconnect.api.AirlineAPI;
import com.chainerist.blockchain.manager.airconnect.builder.SpecificationBuilder;
import com.chainerist.blockchain.manager.airconnect.domain.Aircraft;
import com.chainerist.blockchain.manager.airconnect.domain.Airline;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.dto.AirlineDTO;
import com.chainerist.blockchain.manager.airconnect.dto.SearchDTO;
import com.chainerist.blockchain.manager.airconnect.entity.AirlineEntity;
import com.chainerist.blockchain.manager.airconnect.mapper.AirlineDTOMapper;
import com.chainerist.blockchain.manager.airconnect.service.AirlineService;
import com.chainerist.blockchain.manager.airconnect.util.SearchCriteria;
import com.chainerist.blockchain.manager.util.AuthenticationUtil;
import com.chainerist.blockchain.manager.util.response.ChaineristGenerator;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class AirlineController implements AirlineAPI {

    private AirlineService airlineService;
    private AirlineDTOMapper airlineDTOMapper;

    public static final String SEARCHCRITERIALIST = "searchcriterialist";

    private ObjectMapper objectMapper;


    public AirlineController(AirlineService airlineService, AirlineDTOMapper airlineDTOMapper, ObjectMapper objectMapper){
        this.airlineService = airlineService;
        this.airlineDTOMapper = airlineDTOMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public ChaineristResponse<Object> getByIata(String iata) {
        Airline airline = airlineService.findByIata(iata);
        return ChaineristGenerator.generateSignResponse(airlineDTOMapper.toDTO(airline));
    }

    @Override
    public ChaineristResponse<Object> save(AirlineDTO airlineDTO) {
        Airline airline = airlineDTOMapper.toDomain(airlineDTO);
        airline = airlineService.createAirline(airline);
        return ChaineristGenerator.generateSignResponse(airlineDTOMapper.toDTO(airline));
    }

    @Override
    public ChaineristResponse<Object> update(AirlineDTO airlineDTO) {
        return save(airlineDTO);
    }

    @Override
    public ChaineristResponse<Object> getPage(Map<String, Object> header, int page, int size, String[] sortBy) {
        ChaineristPageable<Airline> airlinePage = airlineService.findAll(page, size);
        return ChaineristGenerator.generateSignResponse(airlinePage);
    }

    @Override
    public ChaineristResponse<Object> searchAirline(Map<String, Object> header, int pageNum, int pageSize) {
        List<SearchCriteria>  criteriaList = convertHeader(header);

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

        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("iata").ascending());
        ChaineristPageable<Airline> airlinePage = airlineService.findBySearchCriteria(builder.buildAirline(), page);
        airlinePage = new ChaineristPageable<>(airlinePage.getTotalElements(), airlinePage.getTotalPages(), airlinePage.getPageable(), airlinePage.getContents());


        return ChaineristGenerator.generateSignResponse(airlinePage);
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
        return new ArrayList<SearchCriteria>(Arrays.asList(searchCriterias));
    }
}
