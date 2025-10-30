package com.example.anagrafica_be.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "residenza")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Residenza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String indirizzo;
    private String cap;
    private String citta;

    @ManyToOne
    @JoinColumn(name = "id_anagrafica", referencedColumnName = "id")
    @JsonBackReference
    private Persona persona;
}
