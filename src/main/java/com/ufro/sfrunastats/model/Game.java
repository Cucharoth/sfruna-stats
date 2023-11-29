package com.ufro.sfrunastats.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "juego")
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "juego_id", nullable = false)
    private int id;

    @Column(name = "nombre_juego", nullable = false, unique = true, length = 30)
    private String name;
}
