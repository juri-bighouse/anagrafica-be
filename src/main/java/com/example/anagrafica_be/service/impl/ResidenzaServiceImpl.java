package com.example.anagrafica_be.service.impl;

import com.example.anagrafica_be.dto.Persona;
import com.example.anagrafica_be.dto.Residenza;
import com.example.anagrafica_be.repository.PersonaRepository;
import com.example.anagrafica_be.repository.ResidenzaRepository;
import com.example.anagrafica_be.service.ResidenzaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidenzaServiceImpl implements ResidenzaService {

    private final ResidenzaRepository residenzaRepository;
    private final PersonaRepository personaRepository;

    public ResidenzaServiceImpl(ResidenzaRepository residenzaRepository, PersonaRepository personaRepository) {
        this.residenzaRepository = residenzaRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public Residenza addResidenzaToPersona(Long personaId, Residenza nuovaResidenza) {
        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new RuntimeException("Persona non trovata con id: " + personaId));

        nuovaResidenza.setPersona(persona);
        return residenzaRepository.save(nuovaResidenza);
    }

    @Override
    public Residenza updateResidenza(Long personaId, Residenza residenzaAggiornata) {
        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new RuntimeException("Persona non trovata con id: " + personaId));

        List<Residenza> residenze = residenzaRepository.findByPersona(persona);
        if (residenze.isEmpty()) {
            throw new RuntimeException("Nessuna residenza trovata per la persona con id: " + personaId);
        }

        Residenza residenzaEsistente = residenze.get(0);
        residenzaEsistente.setIndirizzo(residenzaAggiornata.getIndirizzo());
        residenzaEsistente.setCap(residenzaAggiornata.getCap());
        residenzaEsistente.setCitta(residenzaAggiornata.getCitta());

        return residenzaRepository.save(residenzaEsistente);
    }

    @Override
    public List<Persona> getPersoneByIndirizzo(String indirizzo) {
        List<Residenza> residenze = residenzaRepository.findByIndirizzo(indirizzo);
        return residenze.stream()
                .map(Residenza::getPersona)
                .toList();
    }
}
