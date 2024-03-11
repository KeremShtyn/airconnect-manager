package com.chainerist.blockchain.manager.util.base;

import java.util.List;
import java.util.Set;

public interface BaseDTOMapper<Domain, DTO> {

    Domain toDomain(DTO dto);

    List<DTO> toListDTO(List<Domain> entityObjects);

    DTO toDTO(Domain domain);

    List<Domain> toDomainList(List<DTO> dtos);

}
