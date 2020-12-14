package com.ufrn.embarcados.reaqua.controller;

import com.ufrn.embarcados.reaqua.model.Tower;
import com.ufrn.embarcados.reaqua.service.ApplicationUserService;
import com.ufrn.embarcados.reaqua.service.TowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final TowerService towerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String signin(){
        return "sign-in";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public ModelAndView signup(){
        ModelAndView mv = new ModelAndView("sign-up");
        Iterable<Tower> towers = towerService.listAll();
        mv.addObject("towers", towers);
        return mv;
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String resetPassword(){
        return "reset-password";
    }

}
