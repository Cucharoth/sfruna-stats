package com.ufro.sfrunastats.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufro.sfrunastats.model.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

    @Query(value = "INSERT INTO stats.\"usuario_juega_juego\" (usuario_id, juego_id, tiempo_inicio) " +
            "VALUES ('?2', '?1', LOCALTIMESTAMP) " +
            "ON CONFLICT ON CONSTRAINT usuario_juega_juego_pkey " +
            "DO UPDATE SET tiempo_inicio = LOCALTIMESTAMP;", nativeQuery = true)
    void guardarTiempoInicioJuego(int gameId, int userId);

    @Query(value = "WITH tiempo AS (" +
            "SELECT (LOCALTIMESTAMP - tiempo_inicio) AS intervalo " +
            "FROM stats.\"usuario_juega_juego\" " +
            "WHERE usuario_id = ?2 AND juego_id = ?1) " +
            "UPDATE stats.\"usuario_juega_juego\" " +
            "SET tiempo_jugado = (tiempo_jugado + intervalo) " +
            "FROM tiempo " +
            "WHERE usuario_id = ?2 AND juego_id = ?1;", nativeQuery = true)
    void guardarTiempoJugado(int gameId, int userId);

    @Query(value = "SELECT nombre_juego, COUNT(*) as play_count\r\n" + //
            "FROM stats.\"usuario_juega_juego\"\r\n" + //
            "JOIN stats.\"juego\" USING (juego_id)\r\n" + //
            "GROUP BY nombre_juego\r\n" + //
            "ORDER BY play_count DESC\r\n" + //
            "LIMIT 5\r\n" + //
            "", nativeQuery = true)
    List<String> getMostPopularGames();

}
