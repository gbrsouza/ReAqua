package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.model.ApplicationUser;
import com.ufrn.embarcados.reaqua.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationUserService {
    private final ApplicationUserRepository userRepository;

    public ApplicationUser getUserByEmail (String email) {return userRepository.findByEmail(email);}
}
