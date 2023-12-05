package com.ufro.sfrunastats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufro.sfrunastats.service.GameService;
import com.ufro.sfrunastats.service.StatsService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping
@AllArgsConstructor
public class statsController {

    private final StatsService statsService;
    private final GameService gameService;

    @GetMapping(path = "/")
    public String index(Model model) {

        // System.out.println(statsService.getUserWithTheMostAchvPoints());
        
        // todo: TEST THIS!
        // gameService.guardarTiempoInicioJuego(0, 0);
        // gameService.guardTiempoJugado();
        
        System.out.println(statsService.getMostPopularGames());
        return "index";
    }
}
