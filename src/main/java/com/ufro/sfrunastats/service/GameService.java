package com.ufro.sfrunastats.service;

import org.springframework.stereotype.Service;

import com.ufro.sfrunastats.repository.GameRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    /**
     * Guardará en base de datos la fecha y hora en que el juego se ha iniciado
     * por el usuario.
     * 
     * @param juegoId   id del juego que el usuario ha iniciado.
     * @param usuarioId id del usuario que ha iniciado el juego.
     * 
     */
    public void guardarTiempoInicioJuego(int gameId, int userId) {
        gameRepository.guardarTiempoInicioJuego(gameId, userId);
    }

    /**
     * Guardará en base de datos el tiempo total de juego usando como referencia
     * el tiempo de inicio.
     * 
     * @param juegoId   id del juego que el usuario ha iniciado
     * @param usuarioId id del usuario que ha iniciado el juego
     */
    public void guardarTiempoJugado(int gameId, int userId) {
        gameRepository.guardarTiempoJugado(gameId, userId);
    }
}
