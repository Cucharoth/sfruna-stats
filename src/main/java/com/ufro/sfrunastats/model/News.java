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
@Table(name = "noticia")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noticia_id", nullable = false)
    private int id;

    @Column(name = "titulo_noticia", nullable = false, unique = true, length = 70)
    private String title;

    @Column(name = "content_noticia", nullable = false, columnDefinition = "TEXT")
    private String content;
}
