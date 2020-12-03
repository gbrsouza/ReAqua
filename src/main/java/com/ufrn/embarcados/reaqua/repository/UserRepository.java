package com.ufrn.embarcados.reaqua.repository;

import com.ufrn.embarcados.reaqua.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
