package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Airport;
import com.chainerist.blockchain.manager.airconnect.entity.AirportEntity;
import com.chainerist.blockchain.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirportMapper extends BaseMapper<AirportEntity, Airport> {
}
