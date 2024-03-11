package com.chainerist.blockchain.manager.authentication.repository;


import com.chainerist.blockchain.manager.authentication.entity.WorkingUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface WorkingUnitRepository extends JpaRepository<WorkingUnitEntity, String> {

}
