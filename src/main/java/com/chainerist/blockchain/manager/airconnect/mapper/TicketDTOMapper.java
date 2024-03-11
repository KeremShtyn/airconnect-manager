package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Ticket;
import com.chainerist.blockchain.manager.airconnect.dto.TicketDTO;
import com.chainerist.blockchain.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketDTOMapper extends BaseDTOMapper<Ticket, TicketDTO> {
}
