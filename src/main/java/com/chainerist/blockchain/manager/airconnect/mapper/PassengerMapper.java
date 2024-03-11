package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Passenger;
import com.chainerist.blockchain.manager.airconnect.entity.PassengerEntity;
import com.chainerist.blockchain.manager.authentication.domain.User;
import com.chainerist.blockchain.manager.authentication.entity.UserEntity;
import com.chainerist.blockchain.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PassengerMapper extends BaseMapper<PassengerEntity, Passenger> {


    //Set<Domain> toSetDomainObject(Set<Entity> entityObjects);

}
