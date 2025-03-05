package com.formula1.pitstop.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "coches")
@Data
//@Getter
//@Setter
//@AllArgsConstructor
@NoArgsConstructor

public class Coche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private String motor;
    private String escuderia;
}
