package com.formula1.pitstop.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pilotos")
@Data
//@Getter
//@Setter
//@AllArgsConstructor
@NoArgsConstructor

public class Piloto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String nacionalidad;
    private int edad;
}
