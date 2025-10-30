package com.example.anagrafica_be.service.impl;

import com.example.anagrafica_be.dto.Persona;
import com.example.anagrafica_be.repository.PersonaRepository;
import com.example.anagrafica_be.repository.ResidenzaRepository;
import com.example.anagrafica_be.service.PersonaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final ResidenzaRepository residenzaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository, ResidenzaRepository residenzaRepository) {
        this.personaRepository = personaRepository;
        this.residenzaRepository = residenzaRepository;
    }

    @Override
    public List<Persona> getAllPersone() {
        return personaRepository.findAll();
    }

    @Override
    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona non trovata con id: " + id));
    }

    @Override
    public Optional<Persona> findPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public Persona savePersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new RuntimeException("Persona non trovata con id: " + id);
        }

        Optional<Persona> persona = personaRepository.findById(id);

        residenzaRepository.findByPersona(persona.get())
                .forEach(residenzaRepository::delete);

        personaRepository.deleteById(id);
    }
}
