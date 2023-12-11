package com.ufro.sfrunastats.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ufro.sfrunastats.model.User;
import com.ufro.sfrunastats.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController {
    private final UserService userService;


    /**
     * Maneja primera pagina a mostrar, util para manipular datos antes de mostrar la primera pagina.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @GetMapping(path = "/")
    public String firstRedirect() {
        return "redirect:/index";
    }

    @GetMapping(path = "/index")
    public String index(Model model, Principal principal){
        // double check para principal, aunque, a este punto principal jamas sera null.
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            if (user != null) {
                model.addAttribute("user", user);
                model.addAttribute("onIndex", true);
            }
        }
        
        return "index";
    }

}
