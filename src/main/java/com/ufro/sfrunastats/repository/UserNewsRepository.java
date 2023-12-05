package com.ufro.sfrunastats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufro.sfrunastats.model.UserNews;

@Repository
public interface UserNewsRepository extends CrudRepository<UserNews, Integer> {

    @Query(value = "SELECT COUNT(*) AS vistas\r\n" + //
            "FROM stats.usuario_lee_noticia\r\n" + //
            "JOIN stats.noticia USING (noticia_id)\r\n" + //
            "WHERE noticia_id = ?1\r\n" + //
            "GROUP BY titulo_noticia\r\n" + //
            "ORDER BY vistas DESC;", nativeQuery = true)
    int getCantidadVistasNoticia(int noticiaId);

    @Query(value = "SELECT COUNT(*) AS vistas\r\n" + //
            "FROM stats.usuario_lee_noticia\r\n" + //
            "JOIN stats.usuario USING (usuario_id)\r\n" + //
            "WHERE usuario_id = ?1\r\n" + //
            "GROUP BY nombre_usuario\r\n" + //
            "ORDER BY vistas DESC;", nativeQuery = true)
    int getCantidadVistasUsuario(int userId);

    @Query(value = "SELECT titulo_noticia, COUNT(*) AS vistas\r\n" + //
            "FROM stats.usuario_lee_noticia\r\n" + //
            "JOIN stats.noticia USING (noticia_id)\r\n" + //
            "GROUP BY titulo_noticia\r\n" + //
            "ORDER BY vistas DESC\r\n" + //
            "LIMIT 5;", nativeQuery = true)
    List<String> getNoticiasMasPopulares();

    @Query(value = "SELECT titulo_noticia, COUNT(*) AS vistas\r\n" + //
            "FROM stats.usuario_lee_noticia\r\n" + //
            "JOIN stats.noticia USING (noticia_id)\r\n" + //
            "WHERE fecha >  CURRENT_DATE - INTERVAL '1 months'\r\n" + //
            "GROUP BY titulo_noticia\r\n" + //
            "ORDER BY vistas DESC\r\n" + //
            "LIMIT 5;", nativeQuery = true)
    List<String> getNoticiasMasPopularesThisMonth();

}
