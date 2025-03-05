package com.formula1.pitstop.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "equipos")
@Data
//@Getter
//@Setter
//@AllArgsConstructor
@NoArgsConstructor

public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToOne
    @JoinColumn(name = "piloto_id")
    private Piloto piloto;

    @OneToOne
    @JoinColumn(name = "coche_id")
    private Coche coche;
}
