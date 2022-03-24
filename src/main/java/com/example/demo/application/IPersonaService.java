package com.example.demo.application;

import com.example.demo.infrastructure.DTO.input.PersonaInputDTO;
import com.example.demo.infrastructure.DTO.output.PersonaOutputDTO;

import java.util.List;

public interface IPersonaService {

    PersonaOutputDTO savePersona(PersonaInputDTO inputDTO);
    List<PersonaOutputDTO> findAllPersonas();
    PersonaOutputDTO updatePerson(String id,PersonaInputDTO personaInputDTO);
    void deletePerson(String id);

}
