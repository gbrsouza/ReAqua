package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.model.User;
import com.ufrn.embarcados.reaqua.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserRepository userRepository;

    public User getUserByEmail (String email) {return userRepository.findByEmail(email);}
}
