package com.ufro.sfrunastats.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufro.sfrunastats.model.UserAchv;
import com.ufro.sfrunastats.repository.GameRepository;
import com.ufro.sfrunastats.repository.UserAchvRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StatsService {

    private final UserAchvRepository userAchvRepository;
    private final GameRepository gameRepository;

    // public UserAchv findAllByUserId(int userId) {
    //     return userAchvRepository.findTopAllByUserIdOrderByAchvAchivPointsDesc(userId);
    // }

    /**
     * Obtiene los puntos de logro de un usuario usando como parámetro el ID del
     * usuario.
     * 
     * @param userId ID del usuario a consultar
     * @return Lista con el resultado del Query realizado
     * 
     */
    public int getPuntosLogroByUserId(int userId) {
        return userAchvRepository.getPuntosLogroByUserId(userId);
    }

    
    public List<String> getUserWithTheMostAchvPoints(){
        return userAchvRepository.getUserWithTheMostAchvPoints();
    }

    
    
    
}
