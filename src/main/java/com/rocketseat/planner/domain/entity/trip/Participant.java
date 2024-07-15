package com.rocketseat.planner.domain.entity.trip;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "participants")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Participant  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(name = "is_confirmed", nullable = false)
    private boolean isConfirmed;

    @ManyToOne // uma viagem pode ter varios participantes, e um participante pode pertencer apenas uma viagem
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
}

//21:31 2-aula