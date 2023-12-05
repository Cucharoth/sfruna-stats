package com.ufro.sfrunastats.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufro.sfrunastats.model.User;
import com.ufro.sfrunastats.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public List<String> comparaStats(int userId, int userId2) {
        return userRepository.comparaStats(userId, userId2);
    }

}
