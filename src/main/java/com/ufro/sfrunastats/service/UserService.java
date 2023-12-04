package com.ufro.sfrunastats.service;

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

}
