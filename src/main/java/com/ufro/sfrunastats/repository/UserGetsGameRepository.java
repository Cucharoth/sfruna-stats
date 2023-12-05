package com.ufro.sfrunastats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufro.sfrunastats.model.UserGetsGame;

@Repository
public interface UserGetsGameRepository extends CrudRepository<UserGetsGame, Integer> {

    // @Query(value = "INSERT INTO stats.usuario_adquiere_juego (juego_id, usuario_id, fecha) " +
    //         "VALUES ('?1', '?2', CURRENT_DATE) " +
    //         "ON CONFLICT DO NOTHING;", nativeQuery = true)
    // void registrarAdquisicionJuego(int gameId, int userId);

    @Query(value = "SELECT nombre_juego, COUNT(*) AS copias_vendidas " +
            "FROM stats.usuario_adquiere_juego " +
            "JOIN stats.juego USING (juego_id) " +
            "GROUP BY juego_id, nombre_juego " +
            "ORDER BY copias_vendidas DESC " +
            "LIMIT 5;", nativeQuery = true)
    List<String> getBestSellingGame();

    @Query(value = "SELECT nombre_juego, COUNT(*) AS copias_vendidas " +
            "FROM stats.usuario_adquiere_juego " +
            "JOIN stats.juego USING (juego_id) " +
            "WHERE fecha >  CURRENT_DATE - INTERVAL '1 months' " +
            "GROUP BY juego_id, nombre_juego " +
            "ORDER BY copias_vendidas DESC " +
            "LIMIT 5;", nativeQuery = true)
    List<String> getBestSellingGameThisMonth();

    @Query(value = "SELECT COUNT(*) AS compras_realizadas " +
            "FROM stats.usuario_adquiere_juego " +
            "JOIN stats.usuario USING (usuario_id) " +
            "WHERE usuario_id = ?1 " +
            "GROUP BY nombre_usuario " +
            "ORDER BY compras_realizadas DESC;", nativeQuery = true)
    int getCantidadJuegosByUserId(int userId);

    @Query(value = "SELECT nombre_usuario, COUNT(*) AS compras\r\n" + //
            "FROM stats.usuario_adquiere_juego\r\n" + //
            "JOIN stats.usuario USING (usuario_id)\r\n" + //
            "GROUP BY nombre_usuario\r\n" + //
            "ORDER BY compras DESC\r\n" + //
            "LIMIT 5;", nativeQuery = true)
    List<String> getUserWithTheMostBuys();
}
