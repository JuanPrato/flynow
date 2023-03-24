package com.flycorp.fly;

import com.flycorp.fly.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findByLocationContaining(String location);
}
