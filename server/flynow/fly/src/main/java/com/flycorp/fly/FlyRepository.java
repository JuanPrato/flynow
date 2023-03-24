package com.flycorp.fly;

import com.flycorp.fly.entities.Fly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlyRepository extends JpaRepository<Fly, Integer> {

    List<Fly> findByFrom_LocationContainingOrTo_LocationContaining(String from_location, String to_location);

}
