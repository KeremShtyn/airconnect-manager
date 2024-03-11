package com.chainerist.blockchain.manager.airconnect.controller;

import com.chainerist.blockchain.manager.airconnect.api.AircraftAPI;
import com.chainerist.blockchain.manager.airconnect.builder.SpecificationBuilder;
import com.chainerist.blockchain.manager.airconnect.domain.Aircraft;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.dto.AircraftDTO;
import com.chainerist.blockchain.manager.airconnect.dto.SearchDTO;
import com.chainerist.blockchain.manager.airconnect.mapper.AircraftDTOMapper;
import com.chainerist.blockchain.manager.airconnect.service.AircraftService;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class AircraftController implements AircraftAPI {

    private AircraftService aircraftService;
    private AircraftDTOMapper aircraftDTOMapper;

    public static final String SEARCHCRITERIALIST = "searchcriterialist";
    private ObjectMapper objectMapper;


    public AircraftController(AircraftService aircraftService, AircraftDTOMapper aircraftDTOMapper, ObjectMapper objectMapper){
        this.aircraftDTOMapper = aircraftDTOMapper;
        this.aircraftService =  aircraftService;
        this.objectMapper = objectMapper;
    }


    @Override
    public ChaineristResponse<Object> getBylegNo(String legNo) {
        Aircraft aircraft = aircraftService.findByLegNo(legNo);
        return ChaineristGenerator.generateSignResponse(aircraftDTOMapper.toDTO(aircraft));
    }

    @Override
    public ChaineristResponse<Object> save(AircraftDTO aircraftDTO) {
        Aircraft aircraft =
                aircraftDTOMapper.toDomain(aircraftDTO);
        aircraft = aircraftService.createAircraft(aircraft);
        return ChaineristGenerator.generateSignResponse(aircraftDTOMapper.toDTO(aircraft));
    }

    @Override
    public ChaineristResponse<Object> update(AircraftDTO aircraftDTO) {
        return save(aircraftDTO);
    }

    @Override
    public ChaineristResponse<Object> getPage(Map<String, Object> header, int page, int size, String[] sortBy) {
        ChaineristPageable<Aircraft> aircraftPage = aircraftService.findAll(page, size);
        return ChaineristGenerator.generateSignResponse(aircraftPage);
    }

    @Override
    public ChaineristResponse<Object> searchAircraft(Map<String, Object> header, int pageNum, int pageSize) {
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

        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("legNo").ascending());
        ChaineristPageable<Aircraft> aircraftPage = aircraftService.findBySearchCriteria(builder.buildAircraft(), page);
        aircraftPage = new ChaineristPageable<>(aircraftPage.getTotalElements(), aircraftPage.getTotalPages(), aircraftPage.getPageable(), aircraftPage.getContents());


        return ChaineristGenerator.generateSignResponse(aircraftPage);
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
