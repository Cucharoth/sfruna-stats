package com.ufro.sfrunastats.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario_juega_juego")
@IdClass(UserGetsGameId.class)
public class UserPlaysGame {

    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "juego_id", nullable = false)
    private Game game;

    @Column(name = "tiempo_inicio", nullable = true, columnDefinition = "TIMESTAMP")
    private Timestamp timeInit;

    @Column(name = "tiempo_jugado", nullable = false, columnDefinition = "INTERVAL")
    private Timestamp playTime;
}
