package com.ufro.sfrunastats.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.sql.Date;

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
@Table(name = "usuario_adquiere_juego")
@IdClass(UserGetsGameId.class)
public class UserGetsGame {

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

    @Column(name = "fecha", nullable = false, columnDefinition = "DATE")
    private Date fecha;
}