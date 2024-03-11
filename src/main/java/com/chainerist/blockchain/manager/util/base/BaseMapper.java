package com.chainerist.blockchain.manager.util.base;

import java.util.List;
import java.util.Set;

public interface BaseMapper<Entity, Domain> {

    Entity toEntity(Domain domain);

    List<Domain> toListDomainObject(List<Entity> entityObjects);


    Domain toDomainObject(Entity entityObject);

    List<Entity> toEntityList(List<Domain> domains);

}
