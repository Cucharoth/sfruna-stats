package com.ufro.sfrunastats.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ufro.sfrunastats.model.User;
import com.ufro.sfrunastats.model.UserNews;
import com.ufro.sfrunastats.service.UserGetsGameService;
import com.ufro.sfrunastats.service.UserNewsService;
import com.ufro.sfrunastats.service.UserPlaysGameService;
import com.ufro.sfrunastats.service.UserService;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController {
    private final UserService userService;
    private final UserPlaysGameService userPlaysGameService;
    private final UserGetsGameService userGetsGameService;
    private final UserNewsService userNewsService;

    /**
     * Maneja primera pagina a mostrar, util para manipular datos antes de mostrar
     * la primera pagina.
     * 
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @GetMapping(path = "/")
    public String firstRedirect() {
        return "redirect:/index";
    }

    @GetMapping(path = "/index")
    public String index(Model model, Principal principal) {
        // double check para principal, aunque, a este punto principal jamas sera null.
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            if (user != null) {
                List<List<String>> mostPlayedGames = userPlaysGameService.getMostPlayedGames();
                model.addAttribute("user", user);
                model.addAttribute("onIndex", true);
                model.addAttribute("stats", mostPlayedGames);
            }
        }

        return "index";
    }

    @HxRequest
    @GetMapping(path = "/best-selling-games")
    public String bestSellingGames(Model model) {
        List<List<String>> bestSellingGame = userGetsGameService.getBestSellingGame();
        model.addAttribute("stats", bestSellingGame);
        return "fragments/stats.html :: bestSellingGame";
    }

    @HxRequest
    @GetMapping(path = "/most-seen-news")
    public String mostSeenNews(Model model) {
        List<List<String>> mostSeenNews = userNewsService.getNoticiasMasPopulares();
        model.addAttribute("stats", mostSeenNews);
        return "fragments/stats.html :: mostSeenNews";
    }

    @HxRequest
    @GetMapping(path = "/user-with-the-most-buys")
    public String usersWithMostBuys(Model model) {
        List<List<String>> usersWithMostBuys = userGetsGameService.getUserWithTheMostBuys();
        model.addAttribute("stats", usersWithMostBuys);
        return "fragments/stats.html :: userWithTheMostBuys";
    }

    @HxRequest
    @GetMapping(path = "/user-with-the-most-game-time")
    public String userWithTheMostGameTime(Model model) {
        List<List<String>> userWithTheMostGameTime = userPlaysGameService.getUserWithTheMostGameTime();
        model.addAttribute("stats", userWithTheMostGameTime);
        return "fragments/stats.html :: userWithTheMostGameTime";
    }

}
