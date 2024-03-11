package com.chainerist.blockchain.manager.airconnect.builder;

import com.chainerist.blockchain.manager.airconnect.Specification.*;
import com.chainerist.blockchain.manager.airconnect.entity.*;
import com.chainerist.blockchain.manager.airconnect.util.SearchCriteria;
import com.chainerist.blockchain.manager.airconnect.util.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpecificationBuilder {

    private final List<SearchCriteria> params;

    public SpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public final SpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final SpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<AircraftEntity> buildAircraft() {
        if (params.size() == 0) {
            return null;
        }

        Specification<AircraftEntity> result = new AircraftSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++) {
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.valueOf(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new AircraftSpecification(criteria))
                    : Specification.where(result).or(new AircraftSpecification(criteria));
        }

        return result;
    }

    public Specification<TicketEntity> buildTicket() {
        if (params.size() == 0) {
            return null;
        }

        Specification<TicketEntity> result = new TicketSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++) {
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.valueOf(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new TicketSpecification(criteria))
                    : Specification.where(result).or(new TicketSpecification(criteria));
        }

        return result;
    }

    public Specification<AirlineEntity> buildAirline() {
        if (params.size() == 0 || Objects.isNull(params.get(0).getValue()) || params.get(0).getValue().toString().trim().length() == 0) {
            return null;
        }


        Specification<AirlineEntity> result = new AirlineSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++) {
            SearchCriteria criteria = params.get(idx);

            SearchOperation dataOption = criteria.getDataOption() == null ? SearchOperation.ALL : SearchOperation.valueOf(criteria.getDataOption());
            result = dataOption == SearchOperation.ALL
                    ? Specification.where(result).and(new AirlineSpecification(criteria))
                    : Specification.where(result).or(new AirlineSpecification(criteria));
        }

        return result;
    }

    public Specification<AirportEntity> buildAirport() {
        if (params.size() == 0) {
            return null;
        }

        Specification<AirportEntity> result = new AirportSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++) {
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.valueOf(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new AirportSpecification(criteria))
                    : Specification.where(result).or(new AirportSpecification(criteria));
        }

        return result;
    }

    public Specification<FlightEntity> buildFlight() {
        if (params.size() == 0) {
            return null;
        }

        Specification<FlightEntity> result = new FlightSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++) {
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.valueOf(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new FlightSpecification(criteria))
                    : Specification.where(result).or(new FlightSpecification(criteria));
        }

        return result;
    }

    public Specification<PassengerEntity> buildPassenger() {
        if (params.size() == 0) {
            return null;
        }

        Specification<PassengerEntity> result = new PassengerSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++) {
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.valueOf(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new PassengerSpecification(criteria))
                    : Specification.where(result).or(new PassengerSpecification(criteria));
        }

        return result;
    }

}
