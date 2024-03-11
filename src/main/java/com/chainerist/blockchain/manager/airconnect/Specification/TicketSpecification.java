package com.chainerist.blockchain.manager.airconnect.Specification;

import com.chainerist.blockchain.manager.airconnect.domain.Flight;
import com.chainerist.blockchain.manager.airconnect.domain.Gate;
import com.chainerist.blockchain.manager.airconnect.domain.Passenger;
import com.chainerist.blockchain.manager.airconnect.domain.Ticket;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import com.chainerist.blockchain.manager.airconnect.entity.GateEntity;
import com.chainerist.blockchain.manager.airconnect.entity.PassengerEntity;
import com.chainerist.blockchain.manager.airconnect.entity.TicketEntity;
import com.chainerist.blockchain.manager.airconnect.util.SearchCriteria;
import com.chainerist.blockchain.manager.airconnect.util.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;

public class TicketSpecification implements Specification<TicketEntity> {

    private final SearchCriteria searchCriteria;

    public TicketSpecification(final SearchCriteria searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<TicketEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        switch(Objects.requireNonNull(SearchOperation.valueOf(searchCriteria.getOperation()))){
            case CONTAINS:
                if(searchCriteria.getFilterKey().equals("passengerId")){
                    return cb.like(cb.lower(passengerJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }else if (searchCriteria.getFilterKey().equals("gateId")) {
                    return cb.like(cb.lower(gateJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }else if (searchCriteria.getFilterKey().equals("flightId")) {
                    return cb.like(cb.lower(flightJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                if(searchCriteria.getFilterKey().equals("passengerId")){
                    return cb.notLike(cb.lower(passengerJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }else if (searchCriteria.getFilterKey().equals("gateId")) {
                    return cb.notLike(cb.lower(gateJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }else if (searchCriteria.getFilterKey().equals("flightId")) {
                    return cb.notLike(cb.lower(flightJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case BEGINS_WITH:
                if(searchCriteria.getFilterKey().equals("passengerId")){
                    return cb.like(cb.lower(passengerJoin(root).get(searchCriteria.getFilterKey())), strToSearch + "%");
                }else if (searchCriteria.getFilterKey().equals("gateId")) {
                    return cb.like(cb.lower(gateJoin(root).get(searchCriteria.getFilterKey())), strToSearch + "%");
                }else if (searchCriteria.getFilterKey().equals("flightId")) {
                    return cb.like(cb.lower(flightJoin(root).get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case DOES_NOT_BEGIN_WITH:
                if(searchCriteria.getFilterKey().equals("passengerId")){
                    return cb.notLike(cb.lower(passengerJoin(root).get(searchCriteria.getFilterKey())), strToSearch + "%");
                } else if (searchCriteria.getFilterKey().equals("gateId")) {
                    return cb.notLike(cb.lower(gateJoin(root).get(searchCriteria.getFilterKey())), strToSearch + "%");
                }else if (searchCriteria.getFilterKey().equals("flightId")) {
                    return cb.notLike(cb.lower(flightJoin(root).get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case ENDS_WITH:
                if(searchCriteria.getFilterKey().equals("passengerId")){
                    return cb.like(cb.lower(passengerJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }else if (searchCriteria.getFilterKey().equals("gateId")) {
                    return cb.like(cb.lower(gateJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }else if (searchCriteria.getFilterKey().equals("flightId")) {
                    return cb.like(cb.lower(flightJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case DOES_NOT_END_WITH:
                if(searchCriteria.getFilterKey().equals("passengerId")){
                    return cb.notLike(cb.lower(passengerJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }else if (searchCriteria.getFilterKey().equals("gateId")) {
                    return cb.notLike(cb.lower(gateJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }else if (searchCriteria.getFilterKey().equals("flightId")) {
                    return cb.notLike(cb.lower(flightJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case EQUAL:
                if(searchCriteria.getFilterKey().equals("passengerId")){
                    return cb.equal(passengerJoin(root).get(searchCriteria.getFilterKey()), searchCriteria.getValue());
                }else if (searchCriteria.getFilterKey().equals("gateId")) {
                    return cb.equal(cb.lower(gateJoin(root).get(searchCriteria.getFilterKey())), searchCriteria.getValue());
                }else if (searchCriteria.getFilterKey().equals("flightId")) {
                    return cb.equal(cb.lower(flightJoin(root).get(searchCriteria.getFilterKey())), searchCriteria.getValue());
                }
                return cb.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NOT_EQUAL:
                if(searchCriteria.getFilterKey().equals("passengerId")){
                    return cb.notEqual(passengerJoin(root).get(searchCriteria.getFilterKey()), searchCriteria.getValue() );
                }else if (searchCriteria.getFilterKey().equals("gateId")) {
                    return cb.notEqual(cb.lower(gateJoin(root).get(searchCriteria.getFilterKey())), searchCriteria.getValue());
                }else if (searchCriteria.getFilterKey().equals("flightId")) {
                    return cb.notEqual(cb.lower(flightJoin(root).get(searchCriteria.getFilterKey())), searchCriteria.getValue());
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

    private Join<TicketEntity, PassengerEntity> passengerJoin(Root<TicketEntity> root){
        return root.join("passenger");

    }
    private Join<TicketEntity, GateEntity> gateJoin(Root<TicketEntity> root){
        return root.join("gate");

    }
    private Join<TicketEntity, FlightEntity> flightJoin(Root<TicketEntity> root){
        return root.join("flight");

    }
}
