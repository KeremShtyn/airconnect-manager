package com.chainerist.blockchain.manager.airconnect.controller;


import com.chainerist.blockchain.manager.airconnect.api.TicketAPI;
import com.chainerist.blockchain.manager.airconnect.builder.SpecificationBuilder;
import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Ticket;
import com.chainerist.blockchain.manager.airconnect.dto.TicketDTO;
import com.chainerist.blockchain.manager.airconnect.dto.SearchDTO;
import com.chainerist.blockchain.manager.airconnect.mapper.TicketDTOMapper;
import com.chainerist.blockchain.manager.airconnect.service.TicketService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TicketController implements TicketAPI {

    public static final String SEARCHCRITERIALIST = "searchcriterialist";
    private TicketService ticketService;

    private TicketDTOMapper ticketDTOMapper;

    private ObjectMapper objectMapper;

    public TicketController(TicketService ticketService, TicketDTOMapper ticketDTOMapper, ObjectMapper objectMapper) {
        this.ticketService = ticketService;
        this.ticketDTOMapper = ticketDTOMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public ChaineristResponse<Object> save(TicketDTO ticketDTO) {
        Ticket domainObject = ticketDTOMapper.toDomain(ticketDTO);
        domainObject = ticketService.createTicket(domainObject);
        return ChaineristGenerator.generateSignResponse(ticketDTOMapper.toDTO(domainObject));
    }

    @Override
    public ChaineristResponse<Object> update(TicketDTO ticketDTO) {
        return save(ticketDTO);
    }

    @Override
    public ChaineristResponse<Object> getById(String id) {
        Ticket ticket = ticketService.findById(id);
        return ChaineristGenerator.generateSignResponse(ticketDTOMapper.toDTO(ticket));
    }

    @Override
    public ChaineristResponse<Object> getPage(Map<String, Object> header, int page, int size, String[] sortBy) {
        ChaineristPageable<Ticket> ticketPage = ticketService.findAll(page, size);
        return ChaineristGenerator.generateSignResponse(ticketPage);
    }

    @Override
    public ChaineristResponse<Object> searchTicket(Map<String, Object> header, int pageNum, int pageSize) {
        List<SearchCriteria>  criteriaList = convertHeader(header);



        SearchDTO searchDTO = objectMapper.convertValue(header, SearchDTO.class);
        searchDTO.setSearchCriteriaList(criteriaList);

        if(!CollectionUtils.isEmpty(searchDTO.getSearchCriteriaList())){
            searchDTO.getSearchCriteriaList().removeIf(f->f.getFilterKey().equals("id"));
        }

        if(CollectionUtils.isEmpty(searchDTO.getSearchCriteriaList())){
            searchDTO.setSearchCriteriaList(new ArrayList<>());
        }

        SearchCriteria searchCriteria=new SearchCriteria();
        searchCriteria.setValue(AuthenticationUtil.getPassengerInformation().getPassengerId());
        searchCriteria.setOperation("EQUAL");
        searchCriteria.setFilterKey("id");
        searchDTO.getSearchCriteriaList().add(searchCriteria);



        SpecificationBuilder builder = new SpecificationBuilder();
        criteriaList = searchDTO.getSearchCriteriaList();
        if (criteriaList != null) {
            criteriaList.forEach(x -> {
                x.setDataOption(searchDTO.getDataOption());
                builder.with(x);
            });

        }

        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("identifier").ascending());
        ChaineristPageable<Ticket> ticketPage = ticketService.findBySearchCriteria(builder.buildTicket(), page);
        ticketPage = new ChaineristPageable<>(ticketPage.getTotalElements(), ticketPage.getTotalPages(), ticketPage.getPageable(), ticketPage.getContents());


        return ChaineristGenerator.generateSignResponse(ticketPage);
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
