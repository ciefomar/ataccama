package com.mciefova.dbconnector.module.data.service;

import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ConnectionRepository extends CrudRepository<ConnectionEntity, Long>{
    Optional<ConnectionEntity> findByName(String name);
}
