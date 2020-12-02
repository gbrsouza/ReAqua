package com.ufrn.embarcados.reaqua.repository;

import com.ufrn.embarcados.reaqua.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
