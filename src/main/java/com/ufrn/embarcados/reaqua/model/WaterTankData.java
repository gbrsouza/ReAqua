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
    private Integer level;

    @OneToOne
    @JoinColumn(name = "water_tank_id", referencedColumnName = "id")
    private WaterTank waterTank;

    public Integer getLevel() {
        return level;
    }

    public WaterTank getWaterTank() {
        return waterTank;
    }

    public void setWaterTank(WaterTank waterTank) {
        this.waterTank = waterTank;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
