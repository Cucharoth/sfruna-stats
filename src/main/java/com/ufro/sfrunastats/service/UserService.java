package com.ufro.sfrunastats.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ufro.sfrunastats.model.User;
import com.ufro.sfrunastats.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserPlaysGameService userPlaysGameService;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public List<List<String>> comparaStats(int userId, int userId2) {
        return formatCompareStats(userRepository.comparaStats(userId, userId2));
    }

    private List<List<String>> formatCompareStats(List<String> stats) {
        for (String string : stats) {
            System.out.println(string);
        }
        List<List<String>> result = new ArrayList<>();
        String[] user1Stats = stats.get(0).split(",");
        String[] user2Stats = stats.get(1).split(",");
        result.add(List.of(user1Stats[0], user2Stats[0]));
        result.add(List.of("Cantidad Juegos", user1Stats[1], user2Stats[1]));
        result.add(List.of("Noticias Vistas", user1Stats[2], user2Stats[2]));
        result.add(List.of("Puntos de Logro", user1Stats[3], user2Stats[3]));
        result.add(List.of("Tiempo Jugado", userPlaysGameService.formatPlayTime(user1Stats[4]),
                userPlaysGameService.formatPlayTime(user2Stats[4])));
        return result;
    }

}
