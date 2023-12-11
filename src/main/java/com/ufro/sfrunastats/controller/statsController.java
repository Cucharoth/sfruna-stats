package com.ufro.sfrunastats.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufro.sfrunastats.model.User;
import com.ufro.sfrunastats.service.GameService;
import com.ufro.sfrunastats.service.StatsService;
import com.ufro.sfrunastats.service.UserGetsGameService;
import com.ufro.sfrunastats.service.UserNewsService;
import com.ufro.sfrunastats.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping
@AllArgsConstructor
public class StatsController {

    private final UserService userService;
    private final StatsService statsService;
    private final GameService gameService;
    private final UserGetsGameService userGetsGame;
    private final UserNewsService userNewsService;

    @GetMapping(path = "/stats")
    public String index(Model model, Principal principal) {
        User user = new User();

        System.out.println(principal);
        if (principal != null) {
            user = userService.findByUsername(principal.getName());
        }

        // system:
        // System.out.println(statsService.getUserWithTheMostAchvPoints());
        
        // todo: TEST THIS!
        // gameService.guardarTiempoInicioJuego(0, 0);
        // gameService.guardTiempoJugado();
        
        // System.out.println(statsService.getMostPopularGames());

        // TODO: TEST THIS!
        // System.out.println(userGetsGame.registrarAdquisicionJuego(0, 0));

        // System.out.println(userGetsGame.getBestSellingGame());
        // System.out.println(userGetsGame.getBestSellingGameThisMonth());

        // System.out.println(userGetsGame.getCantidadJuegosByUserId(1));

        // System.out.println(userGetsGame.getUserWithTheMostBuys());

        // TODO: TEST THIS!
        // userNewsService.registrarUsuarioLeeNoticia(user, 1);

        // System.out.println(userNewsService.getCantidadVistasNoticia(1));

        // System.out.println(userNewsService.getCantidadVistasUsuario(user.getId()));

        // System.out.println(userNewsService.getNoticiasMasPopulares().toString());

        // System.out.println(userNewsService.getNoticiasMasPopularesThisMonth().toString());

        // System.out.println(userService.comparaStats(user.getId(), 2).toString());

        // user:
        // getPuntosLogroByUserId()
        return "index";
    }
}
