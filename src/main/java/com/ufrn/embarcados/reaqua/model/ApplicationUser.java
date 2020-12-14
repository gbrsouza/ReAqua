package com.ufrn.embarcados.reaqua.model;

import com.ufrn.embarcados.reaqua.util.Role;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private Role role = Role.USER;

    @ManyToOne
    @JoinColumn(name = "tower_id", referencedColumnName = "id")
    private Tower tower;

    public String getEmail() {
        return email;
    }
}
