package com.chainerist.blockchain.manager.airconnect.repository;

import com.chainerist.blockchain.manager.airconnect.entity.GateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface GateRepository extends JpaRepository<GateEntity, String> {

    Optional<GateEntity> findByCode(String code);
}
