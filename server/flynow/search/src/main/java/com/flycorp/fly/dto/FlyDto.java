package com.flycorp.fly.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlyDto {

private Integer id;
private PlaceDto from;
private PlaceDto to;
private Float price;

}
