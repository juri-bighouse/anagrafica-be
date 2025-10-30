package com.example.anagrafica_be.service;

import com.example.anagrafica_be.dto.Persona;
import com.example.anagrafica_be.dto.Residenza;
import java.util.List;

public interface ResidenzaService {

    Residenza addResidenzaToPersona(Long personaId, Residenza nuovaResidenza);

    Residenza updateResidenza(Long personaId, Residenza residenzaAggiornata);

    List<Persona> getPersoneByIndirizzo(String indirizzo);
}
