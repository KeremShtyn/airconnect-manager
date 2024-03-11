package com.chainerist.blockchain.manager.airconnect.repository;

import com.chainerist.blockchain.manager.airconnect.entity.AircraftEntity;
import com.chainerist.blockchain.manager.airconnect.entity.AirlineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<AirlineEntity, String>, JpaSpecificationExecutor<AirlineEntity> {

    Optional<AirlineEntity> findByIata(String iata);

    Optional<AirlineEntity> findById(String id);

}
