package com.ufro.sfrunastats.service;

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

    private String formatPlayTime(String userPlayTime) {
        String result = "";
        String[] intervals = userPlayTime.split(" ");;
        result += intervals[6]+"h ";
        result += intervals[8]+"m ";
        result += intervals[10]+"s ";
        return result;
    }
}
