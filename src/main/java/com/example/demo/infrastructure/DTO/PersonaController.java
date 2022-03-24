package com.example.demo.infrastructure.DTO;

import com.example.demo.application.IPersonaService;
import com.example.demo.infrastructure.DTO.input.PersonaInputDTO;
import com.example.demo.infrastructure.DTO.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    IPersonaService personaService;

    @GetMapping("personas")
    public ResponseEntity <List<PersonaOutputDTO>> getAllPersonas(){
        return new ResponseEntity<>(personaService.findAllPersonas(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonaOutputDTO> addPersona(@RequestBody PersonaInputDTO personaInputDTO){

        return new ResponseEntity<>(personaService.savePersona(personaInputDTO), HttpStatus.CREATED);
    }

    @PutMapping("personas/{id}")
    public ResponseEntity<PersonaOutputDTO> updatePersona(@PathVariable String id, @RequestBody PersonaInputDTO personaInputDTO){
        return new ResponseEntity<>(personaService.updatePerson(id,personaInputDTO),HttpStatus.OK);
    }

    @DeleteMapping("personas/{id}")
    public void deletePersona(@PathVariable String id){
        personaService.deletePerson(id);
    }
}
