package com.flycorp.fly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateFlyDto {

    private PlaceDto from;
    private PlaceDto to;
    private Float price;

}
