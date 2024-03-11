package com.chainerist.blockchain.manager.airconnect.repository;

import com.chainerist.blockchain.manager.airconnect.entity.OperatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<OperatorEntity, String> {

    Optional<OperatorEntity> findByOperatorNo(String operatorNo);
 }
