package com.chainerist.blockchain.manager.airconnect.mapper;

import com.chainerist.blockchain.manager.airconnect.domain.Operator;
import com.chainerist.blockchain.manager.airconnect.entity.OperatorEntity;
import com.chainerist.blockchain.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperatorMapper extends BaseMapper<OperatorEntity, Operator> {
}
