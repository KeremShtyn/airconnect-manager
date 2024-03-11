package com.chainerist.blockchain.manager.airconnect.Specification;

import com.chainerist.blockchain.manager.airconnect.entity.AircraftEntity;
import com.chainerist.blockchain.manager.airconnect.entity.AirlineEntity;
import com.chainerist.blockchain.manager.airconnect.util.SearchCriteria;
import com.chainerist.blockchain.manager.airconnect.util.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;

public class AircraftSpecification implements Specification<AircraftEntity> {

    private final SearchCriteria searchCriteria;

    public AircraftSpecification(final SearchCriteria searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<AircraftEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        switch(Objects.requireNonNull(SearchOperation.valueOf(searchCriteria.getOperation()))){
            case CONTAINS:
                if(searchCriteria.getFilterKey().equals("airlineId")){
                    return cb.like(cb.lower(airlineLeftJoin(root).get("id")), "%" + strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                if(searchCriteria.getFilterKey().equals("airlineId")){
                    return cb.notLike(cb.lower(airlineLeftJoin(root).get("id")), "%" + strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case BEGINS_WITH:
                if(searchCriteria.getFilterKey().equals("airlineId")){
                    return cb.like(cb.lower(airlineLeftJoin(root).get("id")), strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case DOES_NOT_BEGIN_WITH:
                if(searchCriteria.getFilterKey().equals("airlineId")){
                    return cb.notLike(cb.lower(airlineLeftJoin(root).get("id")), strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case ENDS_WITH:
                if(searchCriteria.getFilterKey().equals("airlineId")){
                    return cb.like(cb.lower(airlineLeftJoin(root).get("id")), "%" + strToSearch);
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case DOES_NOT_END_WITH:
                if(searchCriteria.getFilterKey().equals("airlineId")){
                    return cb.notLike(cb.lower(airlineLeftJoin(root).get("id")), "%" + strToSearch);
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case EQUAL:
                if(searchCriteria.getFilterKey().equals("airlineId")){
                    System.out.println(searchCriteria.getValue());
                    return cb.equal(airlineLeftJoin(root).get("id"), searchCriteria.getValue());
                }
                return cb.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NOT_EQUAL:
                if(searchCriteria.getFilterKey().equals("airlineId")){
                    return cb.notEqual(airlineLeftJoin(root).get("id"), searchCriteria.getValue() );
                }
                return cb.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NULL:
                return cb.isNull(root.get(searchCriteria.getFilterKey()));

            case NOT_NULL:
                return cb.isNotNull(root.get(searchCriteria.getFilterKey()));

            case GREATER_THAN:
                return cb.greaterThan(root. get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case GREATER_THAN_EQUAL:
                return cb.greaterThanOrEqualTo(root. get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN:
                return cb.lessThan(root. get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN_EQUAL:
                return cb.lessThanOrEqualTo(root. get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
        }
        return null;
    }

    private Join<AircraftEntity, AirlineEntity> airlineLeftJoin(Root<AircraftEntity> root){
        return root.join("airline");

    }
}
