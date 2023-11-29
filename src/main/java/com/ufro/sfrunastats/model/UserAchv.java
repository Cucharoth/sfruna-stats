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
@Table(name = "usuario_has_logro")
@IdClass(UserAchvId.class)
public class UserAchv {

    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "logro_id", nullable = false)
    private Achievement achv;

    @Column(name = "completado", nullable = true, columnDefinition = "BOOLEAN")
    private boolean completed;

    @Column(name = "tiempo_jugado", nullable = true, columnDefinition = "TIMESTAMP")
    private Timestamp date;
}
