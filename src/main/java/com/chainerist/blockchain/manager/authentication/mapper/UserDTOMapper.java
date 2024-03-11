package com.chainerist.blockchain.manager.authentication.mapper;

import com.chainerist.blockchain.manager.authentication.domain.User;
import com.chainerist.blockchain.manager.authentication.dto.UserDTO;
import com.chainerist.blockchain.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper extends BaseDTOMapper<User, UserDTO> {
}
