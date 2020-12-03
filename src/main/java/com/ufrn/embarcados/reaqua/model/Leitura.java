package com.ufrn.embarcados.reaqua.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @brief Objeto que representa uma leitura enviada quando o nível do reservatório atingir um valor.
 */
@Data
@Entity
public class Leitura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    int nivel; //
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime momentoDaMedicao;

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public LocalDateTime getMomentoDaMedicao() {
        return momentoDaMedicao;
    }

    public void setMomentoDaMedicao(LocalDateTime momentoDaMedicao) {
        this.momentoDaMedicao = momentoDaMedicao;
    }
}
