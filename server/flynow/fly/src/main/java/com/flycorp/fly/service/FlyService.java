package com.flycorp.fly.service;

import com.flycorp.fly.FlyRepository;
import com.flycorp.lib.fly.Fly;
import com.flycorp.lib.fly.Place;
import com.flycorp.lib.fly.RequestCreateFly;
import com.flycorp.fly.entities.FlyEntity;
import com.flycorp.fly.mapper.PlaceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record FlyService(FlyRepository flyRepository) {

    public List<Fly> getFliesByQuery(String query) {

        List<FlyEntity> flies;

        if (query == null) {
            flies = flyRepository.findAll();
        } else {
            flies = flyRepository.findByFrom_LocationContainingOrTo_LocationContaining(query, query);
        }

        return flies.stream().map(flyEntity -> Fly.builder()
                .id(flyEntity.getId())
                .from(Place.builder()
                        .location(flyEntity.getFrom().getLocation())
                        .time(flyEntity.getFrom().getTime())
                        .build())
                .to(Place.builder()
                        .location(flyEntity.getTo().getLocation())
                        .time(flyEntity.getTo().getTime())
                        .build())
                .price(flyEntity.getPrice())
                .build()).collect(Collectors.toList());
    }

    public Integer createNewFly(RequestCreateFly requestCreateFly) {

        FlyEntity saved = flyRepository.save(FlyEntity.builder()
                .to(PlaceMapper.placeDtoToPlaceEntity(requestCreateFly.getTo()))
                .from(PlaceMapper.placeDtoToPlaceEntity(requestCreateFly.getFrom()))
                .price(requestCreateFly.getPrice())
                .build());

        return saved.getId();
    }
}
