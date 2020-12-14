package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailService {

    private ApplicationUserRepository applicationUserRepository;

    public void sendAlert(Long towerId){}

}
