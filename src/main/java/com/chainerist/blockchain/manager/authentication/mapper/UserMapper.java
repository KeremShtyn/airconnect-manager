package com.chainerist.blockchain.manager.authentication.mapper;

import com.chainerist.blockchain.manager.authentication.domain.User;
import com.chainerist.blockchain.manager.authentication.entity.UserEntity;
import com.chainerist.blockchain.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {WorkingUnitMapper.class})
public interface UserMapper extends BaseMapper<UserEntity, User> {
}
