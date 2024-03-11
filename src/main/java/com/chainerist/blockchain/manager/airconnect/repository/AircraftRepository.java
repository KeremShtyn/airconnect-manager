package com.chainerist.blockchain.manager.airconnect.repository;

import com.chainerist.blockchain.manager.airconnect.domain.Aircraft;
import com.chainerist.blockchain.manager.airconnect.entity.AircraftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface AircraftRepository extends JpaRepository<AircraftEntity, String>, JpaSpecificationExecutor<AircraftEntity> {

    Optional<AircraftEntity> findByLegNo(String legNo);
}
