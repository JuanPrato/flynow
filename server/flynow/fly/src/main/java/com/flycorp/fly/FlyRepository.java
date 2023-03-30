package com.flycorp.fly;

import com.flycorp.fly.entities.FlyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlyRepository extends JpaRepository<FlyEntity, Integer> {

    List<FlyEntity> findByFrom_LocationContainingOrTo_LocationContaining(String from_location, String to_location);

}
