package com.flycorp.fly.mapper;

import com.flycorp.fly.entities.PlaceEntity;
import com.flycorp.lib.fly.Place;

public class PlaceMapper {

    static public PlaceEntity placeDtoToPlaceEntity(Place placeDto) {
        return PlaceEntity.builder()
                .location(placeDto.getLocation())
                .time(placeDto.getTime())
                .build();
    }

}
