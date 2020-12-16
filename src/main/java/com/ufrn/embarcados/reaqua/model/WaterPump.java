package com.ufrn.embarcados.reaqua.model;

import com.ufrn.embarcados.reaqua.util.PumpStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class WaterPump {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp lastShutDown;
    private PumpStatus pumpStatus = PumpStatus.OFF;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getLastShutDown() {
        return lastShutDown;
    }

    public void setLastShutDown(Timestamp lastShutDown) {
        this.lastShutDown = lastShutDown;
    }

    public PumpStatus getPumpStatus() {
        return pumpStatus;
    }

    public void setPumpStatus(PumpStatus pumpStatus) {
        this.pumpStatus = pumpStatus;
    }
}
