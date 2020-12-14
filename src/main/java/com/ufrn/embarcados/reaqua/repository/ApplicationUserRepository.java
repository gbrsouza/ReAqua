package com.ufrn.embarcados.reaqua.repository;

import com.ufrn.embarcados.reaqua.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByEmail(String email);
}
