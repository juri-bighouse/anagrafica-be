package com.example.anagrafica_be.service;

import com.example.anagrafica_be.dto.Persona;
import java.util.List;
import java.util.Optional;

public interface PersonaService {

    List<Persona> getAllPersone();

    Persona getPersonaById(Long id);

    Optional<Persona> findPersonaById(Long id);

    Persona savePersona(Persona persona);

    void deletePersona(Long id);
}
