package com.example.anagrafica_be.config;

import com.example.anagrafica_be.dto.Persona;
import com.example.anagrafica_be.repository.PersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadMockPersone(PersonaRepository personaRepository) {
        return args -> {
            if (personaRepository.count() > 0) {
                return;
            }

            List<Persona> persone = List.of(
                    new Persona(null, "Mario", "Rossi", "RSSMRA80A01H501U", LocalDate.of(1980, 1, 1), null),
                    new Persona(null, "Luisa", "Bianchi", "BNCLSU85C41F205X", LocalDate.of(1985, 3, 15), null),
                    new Persona(null, "Giulia", "Verdi", "VRDGLL90E62H501S", LocalDate.of(1990, 5, 22), null),
                    new Persona(null, "Luca", "Neri", "NRILCU88B12L219T", LocalDate.of(1988, 2, 12), null),
                    new Persona(null, "Sara", "Gialli", "GLLSRA92D18H501W", LocalDate.of(1992, 4, 18), null)
            );

            personaRepository.saveAll(persone);
        };
    }
}
