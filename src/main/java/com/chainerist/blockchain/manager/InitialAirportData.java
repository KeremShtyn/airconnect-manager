package com.chainerist.blockchain.manager;


import com.chainerist.blockchain.manager.airconnect.domain.Airport;
import com.chainerist.blockchain.manager.airconnect.service.AirportService;
import com.chainerist.blockchain.manager.authentication.domain.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Profile("development")
@Component
@Slf4j
public class InitialAirportData {

    public static final String IATA = "ABZ";
    public static final String ICAO = "EGPD";
    public static final String COUNTRY = "Scotland";
    public static final String CITY = "Aberdeen";
    public static final String NAME = "Aberdeen Airport ";

    private AirportService airportService;

    public InitialAirportData(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostConstruct
    public void initialValueOFSystem() {
        log.debug("Starting initial value of system");
        Airport airport = null;
        try {
            airport = airportService.findByIata(IATA);
        } catch (Exception e) {
            log.warn("System airport could not found in system by iata {}", IATA);
        }
        if (Objects.isNull(airport)) {
            createAirport(new Airport());
        }
        log.debug("Completed initial value of system");
    }

    private void createAirport(Airport airport) {
        log.debug("Airport creating by iata : {}", IATA);
        airport.setIata(IATA);
        airport.setIcao(ICAO);
        airport.setCountry(COUNTRY);
        airport.setCity(CITY);
        airport.setName(NAME);
        airportService.createAirport(airport);
        log.debug("System user created by userId : {}", airport.getIata());


    }

}
