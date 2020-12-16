package com.ufrn.embarcados.reaqua.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Tower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany
    @JoinColumn(name="water_tank_id", referencedColumnName = "id")
    private List<WaterTank> waterTanks;

    public List<WaterTank> getWaterTanks() {
        return waterTanks;
    }

}
