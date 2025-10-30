package com.example.anagrafica_be.controller;

import com.example.anagrafica_be.dto.Persona;
import com.example.anagrafica_be.dto.Residenza;
import com.example.anagrafica_be.service.PersonaService;
import com.example.anagrafica_be.service.ResidenzaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persone")
public class AnagraficaController {

    private final PersonaService personaService;
    private final ResidenzaService residenzaService;

    public AnagraficaController(PersonaService personaService, ResidenzaService residenzaService) {
        this.personaService = personaService;
        this.residenzaService = residenzaService;
    }

    @GetMapping
    public ResponseEntity<List<Persona>> getAllPersone() {
        List<Persona> persone = personaService.getAllPersone();
        return ResponseEntity.ok(persone);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Optional<Persona> persona = personaService.findPersonaById(id);
        return persona.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        Persona saved = personaService.savePersona(persona);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{personaId}/residenza")
    public ResponseEntity<Residenza> addResidenza(
            @PathVariable Long personaId,
            @RequestBody Residenza nuovaResidenza) {

        Residenza saved = residenzaService.addResidenzaToPersona(personaId, nuovaResidenza);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{personaId}/residenza")
    public ResponseEntity<Residenza> updateResidenza(
            @PathVariable Long personaId,
            @RequestBody Residenza residenzaAggiornata) {

        Residenza updated = residenzaService.updateResidenza(personaId, residenzaAggiornata);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/indirizzo/{indirizzo}")
    public ResponseEntity<List<Persona>> getPersoneByIndirizzo(@PathVariable String indirizzo) {
        List<Persona> persone = residenzaService.getPersoneByIndirizzo(indirizzo);
        return ResponseEntity.ok(persone);
    }
}
