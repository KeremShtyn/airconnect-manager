package com.chainerist.blockchain.manager.airconnect.controller;

import com.chainerist.blockchain.manager.airconnect.api.AirportAPI;
import com.chainerist.blockchain.manager.airconnect.builder.SpecificationBuilder;
import com.chainerist.blockchain.manager.airconnect.domain.Airport;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.dto.AirportDTO;
import com.chainerist.blockchain.manager.airconnect.dto.SearchDTO;
import com.chainerist.blockchain.manager.airconnect.mapper.AirportDTOMapper;
import com.chainerist.blockchain.manager.airconnect.service.AirportService;
import com.chainerist.blockchain.manager.airconnect.util.SearchCriteria;
import com.chainerist.blockchain.manager.util.AuthenticationUtil;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
public class AirportController implements AirportAPI {

    private AirportService airportService;
    private AirportDTOMapper airportDTOMapper;
    public static final String SEARCHCRITERIALIST = "searchcriterialist";
    private ObjectMapper objectMapper;
    public AirportController(AirportService airportService, AirportDTOMapper airportDTOMapper, ObjectMapper objectMapper) {
        this.airportService = airportService;
        this.airportDTOMapper = airportDTOMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public ChaineristResponse<Object> getByIata(String iata) {
        Airport airport = airportService.findByIata(iata);
        return ChaineristGenerator.generateSignResponse(airportDTOMapper.toDTO(airport));
    }

    @Override
    public ChaineristResponse<Object> save(AirportDTO airportDTO) {
        Airport airport = airportDTOMapper.toDomain(airportDTO);
        airport = airportService.createAirport(airport);
        return ChaineristGenerator.generateSignResponse(airportDTOMapper.toDTO(airport));
    }

    @Override
    public ChaineristResponse<Object> update(AirportDTO airportDTO) {
        return save(airportDTO);
    }

    @Override
    public ChaineristResponse<Object> getPage(Map<String, Object> header, int page, int size, String[] sortBy) {
        ChaineristPageable<Airport> airportPage = airportService.findAll(page, size);
        return ChaineristGenerator.generateSignResponse(airportPage);
    }

    @Override
    public ChaineristResponse<Object> searchAirport(Map<String, Object> header, int pageNum, int pageSize) {
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
        ChaineristPageable<Airport> airportPage = airportService.findBySearchCriteria(builder.buildAirport(), page);
        airportPage = new ChaineristPageable<>(airportPage.getTotalElements(), airportPage.getTotalPages(), airportPage.getPageable(), airportPage.getContents());


        return ChaineristGenerator.generateSignResponse(airportPage);
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
