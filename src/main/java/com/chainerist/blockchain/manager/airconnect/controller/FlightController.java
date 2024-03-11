package com.chainerist.blockchain.manager.airconnect.controller;


import com.chainerist.blockchain.manager.airconnect.api.FlightAPI;
import com.chainerist.blockchain.manager.airconnect.builder.SpecificationBuilder;
import com.chainerist.blockchain.manager.airconnect.domain.Airline;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Flight;
import com.chainerist.blockchain.manager.airconnect.domain.Ticket;
import com.chainerist.blockchain.manager.airconnect.dto.FlightDTO;
import com.chainerist.blockchain.manager.airconnect.dto.SearchDTO;
import com.chainerist.blockchain.manager.airconnect.mapper.FlightDTOMapper;
import com.chainerist.blockchain.manager.airconnect.service.FlightService;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class FlightController implements FlightAPI {


    private FlightService flightService;

    private FlightDTOMapper flightDTOMapper;

    public static final String SEARCHCRITERIALIST = "searchcriterialist";
    private ObjectMapper objectMapper;

    public FlightController(FlightService flightService, FlightDTOMapper flightDTOMapper, ObjectMapper objectMapper){
        this.flightDTOMapper = flightDTOMapper;
        this.flightService = flightService;
        this.objectMapper = objectMapper;
    }


    @Override
    public ChaineristResponse<Object> getById(String id) {

        Flight flight = flightService.findById(id);
        return ChaineristGenerator.generateSignResponse(flightDTOMapper.toDTO(flight));
    }

    @Override
    public ChaineristResponse<Object> save(FlightDTO flightDTO) {
        Flight flight = flightDTOMapper.toDomain(flightDTO);
        flight = flightService.createFlight(flight);
        return ChaineristGenerator.generateSignResponse(flightDTOMapper.toDTO(flight));
    }

    @Override
    public ChaineristResponse<Object> update(FlightDTO flightDTO) {
        return save(flightDTO);
    }

    @Override
    public ChaineristResponse<Object> getPage(Map<String, Object> header, int page, int size, String[] sortBy) {
        ChaineristPageable<Flight> flightPage = flightService.findAll(page, size);
        return ChaineristGenerator.generateSignResponse(flightPage);
    }

    @Override
    public ChaineristResponse<Object> searchFlight(Map<String, Object> header, int pageNum, int pageSize) {
        List<SearchCriteria> criteriaList = convertHeader(header);


        SearchDTO searchDTO = objectMapper.convertValue(header, SearchDTO.class);
        searchDTO.setSearchCriteriaList(criteriaList);

        if(!CollectionUtils.isEmpty(searchDTO.getSearchCriteriaList())){
            searchDTO.getSearchCriteriaList().removeIf(f->f.getFilterKey().equals("airlineId"));
        }

        if(CollectionUtils.isEmpty(searchDTO.getSearchCriteriaList())){
            searchDTO.setSearchCriteriaList(new ArrayList<>());
        }

        SearchCriteria searchCriteria=new SearchCriteria();
        searchCriteria.setValue(AuthenticationUtil.getUserInformation().getAirlineId());
        searchCriteria.setOperation("EQUAL");
        searchCriteria.setFilterKey("airlineId");
        searchDTO.getSearchCriteriaList().add(searchCriteria);




        SpecificationBuilder builder = new SpecificationBuilder();
        criteriaList = searchDTO.getSearchCriteriaList();
        if (criteriaList != null) {
            criteriaList.forEach(x -> {
                x.setDataOption(searchDTO.getDataOption());
                builder.with(x);
            });

        }

        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("flightNo").ascending());
        ChaineristPageable<Flight> flightPage = flightService.findBySearchCriteria(builder.buildFlight(), page);
        flightPage = new ChaineristPageable<>(flightPage.getTotalElements(), flightPage.getTotalPages(), flightPage.getPageable(), flightPage.getContents());


        return ChaineristGenerator.generateSignResponse(flightPage);
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
