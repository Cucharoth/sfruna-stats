package com.ufro.sfrunastats.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.ufro.sfrunastats.model.Game;
import com.ufro.sfrunastats.model.User;
import com.ufro.sfrunastats.model.UserGetsGame;
import com.ufro.sfrunastats.repository.GameRepository;
import com.ufro.sfrunastats.repository.UserGetsGameRepository;
import com.ufro.sfrunastats.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserGetsGameService {

    private final UserGetsGameRepository userGetsGameRepository;
    private final GameRepository gameRepository;

    /**
     * Registra en base de datos la adquisición de un juego por parte del usuario.
     * 
     * @param juegoId   id del juego adquirido por el usuario
     * @param usuarioId id del usuario que hizo la adquisición
     */
    public void registrarAdquisicionJuego(int gameId, User user) {
        UserGetsGame userGetsGame = new UserGetsGame();
        try {
            Game game = gameRepository.findById(gameId).orElseThrow();
            if (game != null) {
                userGetsGame.setGame(game);
                userGetsGame.setUser(user);
                userGetsGameRepository.save(userGetsGame);
            }
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
    }

    /**
     * Obtiene una lista con los juegos mas vendidos en la plataforma.
     * 
     * @return Lista con los resultados del Query realizado.
     * 
     */
    public List<String> getBestSellingGame() {
        return userGetsGameRepository.getBestSellingGame();
    }

    /**
     * Obtiene una lista con los juegos mas vendidos en la plataforma los últimos 30
     * días.
     * 
     * @return Lista con los resultados del Query realizado.
     * 
     */
    public List<String> getBestSellingGameThisMonth() {
        return userGetsGameRepository.getBestSellingGameThisMonth();
    }

    /**
     * Obtiene cantidad de juegos que el usuario posee, usando como parámetro el ID
     * del usuario.
     * 
     * @param usuarioId ID del usuario a consultar.
     * @return Lista con el resultado del Query realizado.
     * 
     */
    public int getCantidadJuegosByUserId(int userId) {
        return userGetsGameRepository.getCantidadJuegosByUserId(userId);
    }

    public List<String> getUserWithTheMostBuys() {
        return userGetsGameRepository.getUserWithTheMostBuys();
    }
}
