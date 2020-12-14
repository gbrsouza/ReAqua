package com.ufrn.embarcados.reaqua.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class WaterTankData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp timestamp;
    private Integer capacity;

    @OneToOne
    @JoinColumn(name = "water_tank_id", referencedColumnName = "id")
    private WaterTank waterTank;

}
