package com.ufrn.embarcados.reaqua.controller;

import com.ufrn.embarcados.reaqua.model.ApplicationUser;
import com.ufrn.embarcados.reaqua.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    @RequestMapping(value = "/cadastro/save", method = RequestMethod.POST)
    public String saveUser(ApplicationUser applicationUser){
        applicationUserService.saveUser(applicationUser);
        return "redirect:/login";
    }

}
