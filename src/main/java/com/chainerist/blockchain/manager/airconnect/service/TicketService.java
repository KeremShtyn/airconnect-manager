package com.chainerist.blockchain.manager.airconnect.service;

import com.chainerist.blockchain.manager.airconnect.domain.ChaineristPageable;
import com.chainerist.blockchain.manager.airconnect.domain.Flight;
import com.chainerist.blockchain.manager.airconnect.domain.Passenger;
import com.chainerist.blockchain.manager.airconnect.domain.Ticket;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.airconnect.entity.TicketEntity;
import com.chainerist.blockchain.manager.airconnect.error.ErrorCodes;
import com.chainerist.blockchain.manager.airconnect.mapper.TicketMapper;
import com.chainerist.blockchain.manager.airconnect.repository.TicketRepository;
import com.chainerist.blockchain.manager.util.exception.ChaineristException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class TicketService {

    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper){
        this.ticketMapper = ticketMapper;
        this.ticketRepository = ticketRepository;
    }

    public Ticket findById(String id) {
        return ticketRepository.findById(id).map(ticketMapper::toDomainObject).orElseThrow(() -> new ChaineristException(ErrorCodes.DATA_NOT_FOUND));
    }

    public ChaineristPageable<Ticket> findAll(int page, int size){
        Page<TicketEntity> tickets = ticketRepository.findAll(PageRequest.of(page, size));
        return new ChaineristPageable<Ticket>(tickets.getTotalElements(),tickets.getTotalPages(),tickets.getPageable(),ticketMapper.toListDomainObject(tickets.getContent()));
    }

    @Transactional
    public ChaineristPageable<Ticket> findBySearchCriteria(Specification<TicketEntity> spec, Pageable page){
        Page<TicketEntity> tickets = ticketRepository.findAll(spec, page);
        return new ChaineristPageable<Ticket>(tickets.getTotalElements(),tickets.getTotalPages(),tickets.getPageable(),ticketMapper.toListDomainObject(tickets.getContent()));
    }

    public List<TicketEntity> findAllTicket(){
        return ticketRepository.findAll();
    }

    public Ticket createTicket(Ticket domainObject) {
        this.validateTicket(domainObject);
        return saveOrUpdate(domainObject);
    }

    private Ticket saveOrUpdate(Ticket domainObject) {
        return ticketMapper.toDomainObject(ticketRepository.save(ticketMapper.toEntity(domainObject)));
    }

    private void validateTicket(Ticket domainObject) {

        if (Objects.isNull(domainObject)) {
            throw new ChaineristException(ErrorCodes.TICKET_DATA_CAN_NOT_BE_EMPTY);
        }

        if (StringUtils.isEmpty(domainObject.getIdentifier())) {
            throw new ChaineristException(ErrorCodes.TICKET_IDENTIFIER_CAN_NOT_BE_EMPTY);
        }

        if (StringUtils.isEmpty(domainObject.getSeat())) {
            throw new ChaineristException(ErrorCodes.TICKET_SEAT_CAN_NOT_BE_EMPTY);
        }
    }

}
