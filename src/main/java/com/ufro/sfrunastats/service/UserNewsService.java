package com.ufro.sfrunastats.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ufro.sfrunastats.model.News;
import com.ufro.sfrunastats.model.User;
import com.ufro.sfrunastats.model.UserNews;
import com.ufro.sfrunastats.repository.NewsRepository;
import com.ufro.sfrunastats.repository.UserNewsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserNewsService {

    private final UserNewsRepository userNewsRepository;
    private final NewsRepository newsRepository;

    /**
     * Registra en base de datos el momento en que un usuario lee una noticia,
     * usando como parámetro la <i>ID</i> del usuario y la <i>ID</i> de la noticia
     * que se ha leído.
     * 
     * @param noticiaId id de la noticia que ha leído.
     * @param user      User que ha realizado la lectura.
     * 
     */
    public void registrarUsuarioLeeNoticia(User user, int newsId) {
        UserNews userNews = new UserNews();
        try {
            News news = newsRepository.findById(newsId).orElseThrow();
            if (news != null) {
                userNews.setUser(user);
                userNews.setNews(news);
                userNewsRepository.save(userNews);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Obtiene la cantidad de vistas que ha recibido una noticia.
     * 
     * @param newsId id de la noticia a ser consultada.
     * @return Lista con el resultado del Query realizado.
     * 
     */
    public int getCantidadVistasNoticia(int newsId) {
        return userNewsRepository.getCantidadVistasNoticia(newsId);
    }

    /**
     * Obtiene la cantidad total de vistas a <i>Noticias</i> que un usuario ha
     * realizado.
     * 
     * @param userId id del usuario a consultar.
     * @return Lista con el resultado del Query realizado.
     * 
     */
    public int getCantidadVistasUsuario(int userId) {
        return userNewsRepository.getCantidadVistasUsuario(userId);
    }

    /**
     * Obtiene las <i>Noticias</i> que registran una mayor cantidad de vistas.
     * 
     * @return Lista con el resultado del Query realizado.
     * 
     */
    public List<List<String>> getNoticiasMasPopulares() {
        List<List<String>> result = new ArrayList<>();
        List<String> mostSeenNews = userNewsRepository.getNoticiasMasPopulares();
        if (mostSeenNews != null) {
            for (String row : mostSeenNews) {
                String[] splitArr = row.split(",", 2);
                result.add(List.of(splitArr[0], splitArr[1]));
            }
        }
        return result;
    }

    /**
     * Obtiene las <i>Noticias</i> que registran una mayor cantidad de vistas en los
     * últimos 30 días.
     * 
     * @return Lista con el resultado del Query realizado.
     * 
     */
    public List<String> getNoticiasMasPopularesThisMonth() {
        return userNewsRepository.getNoticiasMasPopularesThisMonth();
    }
}
