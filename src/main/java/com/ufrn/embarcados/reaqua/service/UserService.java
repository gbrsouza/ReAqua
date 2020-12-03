package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.model.Usuario;
import com.ufrn.embarcados.reaqua.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private UserRepository userRepository;

    public Usuario getUserByEmail (String email) {return userRepository.findByEmail(email);}
}
