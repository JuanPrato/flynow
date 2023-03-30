package com.flycorp.lib.fly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateFly {

    private Place from;
    private Place to;
    private Float price;

}
