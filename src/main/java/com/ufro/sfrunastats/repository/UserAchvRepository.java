package com.ufro.sfrunastats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufro.sfrunastats.model.Achievement;
import com.ufro.sfrunastats.model.User;
import com.ufro.sfrunastats.model.UserAchv;

@Repository
public interface UserAchvRepository extends CrudRepository<UserAchv, Integer> {

        //UserAchv findTopAllByUserIdOrderByAchvAchivPointsDesc(int userId);


        @Query(value = "SELECT SUM(puntos_logro) as total_puntos_logro\r\n" + //
                        "FROM stats.usuario_has_logro\r\n" + //
                        "JOIN stats.usuario USING (usuario_id)\r\n" + //
                        "JOIN stats.logro USING (logro_id)\r\n" + //
                        "WHERE usuario_id = ?1\r\n" + //
                        "GROUP BY nombre_usuario;", nativeQuery = true)
        int getPuntosLogroByUserId(int userId);

        @Query(value = "SELECT nombre_usuario, SUM(puntos_logro) AS puntos_logro\r\n" + //
                        "FROM stats.usuario_has_logro\r\n" + //
                        "JOIN stats.usuario USING (usuario_id)\r\n" + //
                        "JOIN stats.logro USING (logro_id)\r\n" + //
                        "GROUP BY nombre_usuario\r\n" + //
                        "ORDER BY puntos_logro DESC\r\n" + //
                        "LIMIT 5;", nativeQuery = true)
        List<String> getUserWithTheMostAchvPoints();

        List<UserAchv> findAllByUserId(int userId);
}
