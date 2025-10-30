package com.example.anagrafica_be.repository;

import com.example.anagrafica_be.dto.Residenza;
import com.example.anagrafica_be.dto.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidenzaRepository extends JpaRepository<Residenza, Long> {


    List<Residenza> findByIndirizzo(String indirizzo);


    List<Residenza> findByPersona(Persona persona);
}

