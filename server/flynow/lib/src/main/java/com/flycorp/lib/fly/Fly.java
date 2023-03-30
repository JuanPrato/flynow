package com.flycorp.lib.fly;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fly {
    private Integer id;
    private Place from;
    private Place to;
    private Float price;
}
