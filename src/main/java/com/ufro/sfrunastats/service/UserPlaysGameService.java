package com.ufro.sfrunastats.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.sqm.IntervalType;
import org.springframework.stereotype.Service;

import com.ufro.sfrunastats.repository.UserPlaysGameRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserPlaysGameService {
    
    private final UserPlaysGameRepository userPlaysGameRepository;

    public String getTotalGameTimeByUserId(int userId) {
        String userPlayTime = userPlaysGameRepository.getTotalGameTimeByUserId(userId);
        return formatPlayTime(userPlayTime);
    }

    public String formatPlayTime(String userPlayTime) {
        String result = "";
        String[] intervals = userPlayTime.split(" ");
        result += intervals[6]+"h ";
        result += intervals[8]+"m ";
        result += intervals[10]+"s ";
        return result;
    }

    /**
     * Obtiene una lista con los juegos mas populares ordenados de forma
     * descendente.
     * 
     * @return Lista con resultado del Query realizado.
     */
    public List<List<String>> getMostPlayedGames() {
        List<List<String>> result = new ArrayList<>();
        List<String> mostPlayerGames = userPlaysGameRepository.getMostPlayedGames();
        if (mostPlayerGames != null) {
            for (String row : mostPlayerGames) {
                String[] splitArr = row.split(",", 2);
                result.add(List.of(splitArr[0], formatPlayTime(splitArr[1])));
            }
        }
        return result;
    }

    public List<List<String>> getUserWithTheMostGameTime() {
        List<List<String>> result = new ArrayList<>();
        List<String> userWithTheMostPlayTime = userPlaysGameRepository.getUserWithTheMostGameTime();
        if (userWithTheMostPlayTime != null) {
            for (String row : userWithTheMostPlayTime) {
                String[] splitArr = row.split(",", 2);
                result.add(List.of(splitArr[0], formatPlayTime(splitArr[1])));
            }
        }
        return result;
    }
}
