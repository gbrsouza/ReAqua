package com.ufrn.embarcados.reaqua.repository;

import com.ufrn.embarcados.reaqua.model.ApplicationUser;
import com.ufrn.embarcados.reaqua.model.Tower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByEmail(String email);

    List<ApplicationUser> findByTower(Tower tower);
}
