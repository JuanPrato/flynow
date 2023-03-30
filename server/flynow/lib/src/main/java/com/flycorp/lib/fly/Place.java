package com.flycorp.lib.fly;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    private String location;
    private Calendar time;
}
