package com.flycorp.fly.service;

import com.flycorp.fly.FlyRepository;
import com.flycorp.fly.PlaceRepository;
import com.flycorp.fly.dto.FlyDto;
import com.flycorp.fly.dto.PlaceDto;
import com.flycorp.fly.dto.RequestCreateFlyDto;
import com.flycorp.fly.entities.Fly;
import com.flycorp.fly.entities.Place;
import com.flycorp.fly.mapper.PlaceMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public record FlyService(FlyRepository flyRepository, PlaceRepository placeRepository) {

    public List<FlyDto> getFliesByQuery(String query) {

        List<Fly> flies;

        if (query == null) {
            flies = flyRepository.findAll();
        } else {
            flies = flyRepository.findByFrom_LocationContainingOrTo_LocationContaining(query, query);
        }

        return flies.stream().map(fly -> FlyDto.builder()
                .id(fly.getId())
                .from(PlaceDto.builder()
                        .location(fly.getFrom().getLocation())
                        .time(fly.getFrom().getTime())
                        .build())
                .to(PlaceDto.builder()
                        .location(fly.getTo().getLocation())
                        .time(fly.getTo().getTime())
                        .build())
                .price(fly.getPrice())
                .build()).collect(Collectors.toList());
    }

    public Integer createNewFly(RequestCreateFlyDto requestCreateFly) {

        Fly saved = flyRepository.save(Fly.builder()
                .to(PlaceMapper.placeDtoToPlace(requestCreateFly.getTo()))
                .from(PlaceMapper.placeDtoToPlace(requestCreateFly.getFrom()))
                .price(requestCreateFly.getPrice())
                .build());

        return saved.getId();
    }
}
