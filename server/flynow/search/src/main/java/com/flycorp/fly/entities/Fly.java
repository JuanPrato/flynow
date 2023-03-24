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
@Entity
public class Fly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne(cascade = CascadeType.ALL)
    private Place from;

    @OneToOne(cascade = CascadeType.ALL)
    private Place to;

    private Float price;
}
