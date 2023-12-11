package com.ufro.sfrunastats.repository;

import java.util.List;

import org.hibernate.query.sqm.IntervalType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufro.sfrunastats.model.User;

@Repository
public interface UserPlaysGameRepository extends CrudRepository<User, Integer>{

    @Query(value = "SELECT SUM(tiempo_jugado) AS tiempo_jugado\r\n" + //
            "FROM stats.usuario_juega_juego\r\n" + //
            "JOIN stats.usuario USING (usuario_id)\r\n" + //
            "WHERE usuario_id = ?1\r\n" + //
            "GROUP BY nombre_usuario;", nativeQuery = true)
    String getTotalGameTimeByUserId(int userId);

    @Query(value = "SELECT nombre_juego, SUM(tiempo_jugado) AS tiempo_jugado\r\n" + //
            "FROM stats.usuario_juega_juego\r\n" + //
            "JOIN stats.juego USING (juego_id)\r\n" + //
            "GROUP BY nombre_juego\r\n" + //
            "ORDER BY tiempo_jugado DESC\r\n" + //
            "LIMIT 5;", nativeQuery = true)
    List<String> getMostPlayedGames();

    @Query(value = "SELECT nombre_usuario, SUM(tiempo_jugado) AS tiempo_jugado\r\n" + //
                    "FROM stats.usuario_juega_juego\r\n" + //
                    "JOIN stats.usuario USING (usuario_id)\r\n" + //
                    "GROUP BY nombre_usuario\r\n" + //
                    "ORDER BY tiempo_jugado DESC\r\n" + //
                    "LIMIT 5;", nativeQuery = true)
    List<String> getUserWithTheMostGameTime();
    
}
