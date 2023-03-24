package com.flycorp.fly.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String location;

    private Calendar time;

    @OneToOne(cascade = CascadeType.ALL)
    private Fly fly;

}
