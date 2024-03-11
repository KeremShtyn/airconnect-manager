package com.chainerist.blockchain.manager.airconnect.repository;

import com.chainerist.blockchain.manager.airconnect.entity.AirportEntity;
import com.chainerist.blockchain.manager.airconnect.entity.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity, String>, JpaSpecificationExecutor<PassengerEntity> {

    Optional<PassengerEntity> findByIdentifier(String identifier);

    List<PassengerEntity> findByFirstName(String firstname);

}
