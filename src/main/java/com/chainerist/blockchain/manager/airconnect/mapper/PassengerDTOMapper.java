package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Passenger;
import com.chainerist.blockchain.manager.airconnect.dto.PassengerDTO;
import com.chainerist.blockchain.manager.airconnect.entity.PassengerEntity;
import com.chainerist.blockchain.manager.util.base.BaseDTOMapper;
import com.chainerist.blockchain.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerDTOMapper extends BaseDTOMapper<Passenger, PassengerDTO> {


}
