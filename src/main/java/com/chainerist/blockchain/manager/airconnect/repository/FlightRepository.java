package com.chainerist.blockchain.manager.airconnect.repository;

import com.chainerist.blockchain.manager.airconnect.entity.AircraftEntity;
import com.chainerist.blockchain.manager.airconnect.entity.FlightEntity;
import net.bytebuddy.jar.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, String>, JpaSpecificationExecutor<FlightEntity> {

    Optional<FlightEntity> findByAirline(String Airline);

}
