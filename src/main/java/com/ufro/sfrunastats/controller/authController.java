package com.ufro.sfrunastats.controller;

import java.util.Locale.Category;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufro.sfrunastats.model.User;
import com.ufro.sfrunastats.model.utils.Role;
import com.ufro.sfrunastats.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping
@AllArgsConstructor
public class authController {

    private final UserService userService;

    @PostMapping("/new-user")
    public String newUser(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "pass") String pass,
            Model model) {
        User user = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setUsername(name);
        user.setPassword(encoder.encode(pass));
        user.setRole(Role.ADMIN);
        userService.save(user);
        return "index";
    }
}
