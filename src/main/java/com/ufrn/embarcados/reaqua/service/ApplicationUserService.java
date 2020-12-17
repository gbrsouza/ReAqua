package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.model.ApplicationUser;
import com.ufrn.embarcados.reaqua.model.Tower;
import com.ufrn.embarcados.reaqua.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationUserService {
    private final ApplicationUserRepository userRepository;

    public ApplicationUser getUserByEmail (String email) {return userRepository.findByEmail(email);}

    public List<ApplicationUser> getAllByTower(Tower tower) {
        return userRepository.findByTower(tower);
    }

    public void saveUser(ApplicationUser user) { userRepository.save(user); }
}
