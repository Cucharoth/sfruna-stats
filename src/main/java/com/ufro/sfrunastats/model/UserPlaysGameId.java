package com.ufro.sfrunastats.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPlaysGameId implements Serializable {
    private User user;
    private Game game;
}
