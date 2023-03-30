package com.flycorp.fly.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "fly")
public class FlyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne(cascade = CascadeType.ALL)
    private PlaceEntity from;

    @OneToOne(cascade = CascadeType.ALL)
    private PlaceEntity to;

    private Float price;
}
