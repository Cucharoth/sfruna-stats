package com.ufro.sfrunastats.repository;

import com.ufro.sfrunastats.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String name);

    @Query(value = "WITH user_library AS (\r\n" + //
            "SELECT nombre_usuario, COUNT(*) AS games\r\n" + //
            "FROM stats.usuario_adquiere_juego\r\n" + //
            "JOIN stats.usuario USING (usuario_id)\r\n" + //
            "WHERE usuario_id = ?1 OR usuario_id = ?2\r\n" + //
            "GROUP BY nombre_usuario\r\n" + //
            ")\r\n" + //
            ", user_reads_news AS(\r\n" + //
            "SELECT nombre_usuario, COUNT(*) AS vistas_noticias\r\n" + //
            "FROM stats.usuario_lee_noticia\r\n" + //
            "JOIN stats.usuario USING (usuario_id)\r\n" + //
            "WHERE usuario_id = ?1 OR usuario_id = ?2\r\n" + //
            "GROUP BY nombre_usuario\r\n" + //
            ")\r\n" + //
            ", user_puntos_logro AS(\r\n" + //
            "SELECT nombre_usuario, SUM(puntos_logro) AS puntos_logro\r\n" + //
            "FROM stats.usuario_has_logro\r\n" + //
            "JOIN stats.usuario USING (usuario_id)\r\n" + //
            "JOIN stats.logro USING (logro_id)\r\n" + //
            "WHERE usuario_id = ?1 OR usuario_id = ?2\r\n" + //
            "GROUP BY nombre_usuario\r\n" + //
            ")\r\n" + //
            ", user_total_playTime AS (\r\n" + //
            "SELECT nombre_usuario, SUM(tiempo_jugado) AS tiempo_jugado\r\n" + //
            "FROM stats.usuario_juega_juego\r\n" + //
            "JOIN stats.usuario USING (usuario_id)\r\n" + //
            "WHERE usuario_id = ?1 OR usuario_id = ?2\r\n" + //
            "GROUP BY nombre_usuario\r\n" + //
            ")\r\n" + //
            "SELECT\r\n" + //
            "nombre_usuario,\r\n" + //
            "games,\r\n" + //
            "vistas_noticias,\r\n" + //
            "puntos_logro,\r\n" + //
            "tiempo_jugado\r\n" + //
            "FROM stats.usuario\r\n" + //
            "JOIN user_library USING (nombre_usuario)\r\n" + //
            "JOIN user_reads_news USING (nombre_usuario)\r\n" + //
            "JOIN user_puntos_logro USING (nombre_usuario)\r\n" + //
            "JOIN user_total_playTime USING (nombre_usuario)\r\n" + //
            "ORDER BY nombre_usuario ASC;", nativeQuery = true)
    List<String> comparaStats(int userId1, int userId2);
}

