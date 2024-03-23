package com.ufro.sfrunastats.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.dialect.PostgreSQLIntervalSecondJdbcType;
import org.hibernate.query.sqm.IntervalType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufro.sfrunastats.model.Achievement;
import com.ufro.sfrunastats.model.User;
import com.ufro.sfrunastats.model.UserAchv;
import com.ufro.sfrunastats.service.UserAchvService;
import com.ufro.sfrunastats.service.UserGetsGameService;
import com.ufro.sfrunastats.service.UserNewsService;
import com.ufro.sfrunastats.service.UserPlaysGameService;
import com.ufro.sfrunastats.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final UserAchvService userAchvService;
    private final UserGetsGameService userGetsGameService;
    private final UserNewsService userNewsService;
    private final UserPlaysGameService userPlaysGameService;

    @GetMapping(path = "/profile")
    public String index(Model model, Principal principal) {
        // double check para principal, aunque, a este punto principal jamas sera null.
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());

            if (user != null) {
                List<List<String>> stats = constructStats(user);
                model.addAttribute("user", user);
                model.addAttribute("stats", stats);
                model.addAttribute("onProfile", true);
            }
        }

        return "profile";
    }

    private List<List<String>> constructStats(User user) {
        int userAchvPoints = userAchvService.getPuntosLogroByUserId(user.getId());
        int userBuys = userGetsGameService.getCantidadBuysByUserId(user.getId());
        int userNewsViews = userNewsService.getCantidadVistasUsuario(user.getId());
        String userTotalPlayTime = userPlaysGameService
                .getTotalGameTimeByUserId(user.getId());
        List<List<String>> stats = new ArrayList<>();
        stats.add(List.of("Puntos de Logro", String.valueOf(userAchvPoints)));
        stats.add(List.of("Compras Realizadas", String.valueOf(userBuys)));
        stats.add(List.of("Noticias Vistas", String.valueOf(userNewsViews)));
        stats.add(List.of("Tiempo de Juego", userTotalPlayTime));

        return stats;
    }

    @GetMapping(path = "/profile/compare-stats")
    public String compareStats(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);
            model.addAttribute("onProfile", true);
        }
        return "compare-stats";
    }

    @PostMapping(path = "/profile/compare-stats/search")
    public String compareStatsSearch(@RequestParam("keyword") String newUserName,
            Model model, Principal principal) {
        if (principal != null) {
            User newUser = userService.findByUsername(newUserName);
            User user = userService.findByUsername(principal.getName());
            if (newUser != null && !newUser.equals(user)) {
                List<List<String>> stats = userService.comparaStats(user.getId(), newUser.getId());
                model.addAttribute("userName1", stats.get(0).get(0));
                model.addAttribute("userName2", stats.get(0).get(1));
                stats.remove(0);
                model.addAttribute("stats", stats);
            } else {
                model.addAttribute("message", "¡Usuario no encontrado!");
            }
        }
        return "fragments/stats.html :: compare-stats";
    }

    @GetMapping(path = "/profile/achievement")
    public String achievement(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);
            model.addAttribute("onProfile", true);
            List<UserAchv> userAchv = userAchvService.findAllByUserId(user.getId());
            if (userAchv != null) {
                model.addAttribute("achivs", userAchv);
            } else {
                model.addAttribute("message", "¡Aún no posees ningún logro! :[");
            }
        }
        return "achievement";
    }

}
