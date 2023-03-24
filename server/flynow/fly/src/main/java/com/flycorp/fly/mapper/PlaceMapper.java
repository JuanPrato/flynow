package com.flycorp.fly.mapper;

import com.flycorp.fly.dto.PlaceDto;
import com.flycorp.fly.entities.Place;

public class PlaceMapper {

    static public Place placeDtoToPlace(PlaceDto placeDto) {
        return Place.builder()
                .location(placeDto.getLocation())
                .time(placeDto.getTime())
                .build();
    }

}
