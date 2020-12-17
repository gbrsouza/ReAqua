package com.ufrn.embarcados.reaqua.controller;

import com.ufrn.embarcados.reaqua.model.ApplicationUser;
import com.ufrn.embarcados.reaqua.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    @RequestMapping(value = "/cadastro/save", method = RequestMethod.POST)
    public String saveUser(ApplicationUser applicationUser){
        log.info("Encrypting password...");
        if (applicationUser == null)
            log.info("O usuário veio vazio");
        applicationUser.setPassword(new BCryptPasswordEncoder().encode(applicationUser.getPassword()));
        applicationUserService.saveUser(applicationUser);
        return "redirect:/login";
    }

}
