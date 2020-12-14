package com.ufrn.embarcados.reaqua.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class WaterTank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer capacity;
    private Integer currentCapacity;
    private String description;

    @OneToOne
    @JoinColumn(name = "water_pump_id", referencedColumnName = "id")
    private WaterPump waterPump;

}
