package com.ufro.sfrunastats.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

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
@Table(name = "logro")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logro_id", nullable = false)
    private int id;

    @Column(name = "nombre_logro", nullable = false, length = 20)
    private String name;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String description;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "juego_id", nullable = true)
    private Game game;

    @Column(name = "puntos_logro", nullable = false, columnDefinition = "SMALLINT")
    private int achivPoints;
}
