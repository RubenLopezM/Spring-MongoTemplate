package com.example.demo.application;

import com.example.demo.Domain.Persona;
import com.example.demo.Exceptions.PersonNotFoundException;
import com.example.demo.Exceptions.UnprocessableException;
import com.example.demo.infrastructure.DTO.input.PersonaInputDTO;
import com.example.demo.infrastructure.DTO.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public PersonaOutputDTO savePersona(PersonaInputDTO inputDTO) {

        validar(inputDTO);
        Persona persona= mongoTemplate.save(toPersona(inputDTO)) ;
        return new PersonaOutputDTO(persona);
    }

    @Override
    public List<PersonaOutputDTO> findAllPersonas() {
        List<Persona> personas= mongoTemplate.findAll(Persona.class);
        List<PersonaOutputDTO> outputDTOList= personas.stream().map(persona -> convertToDTO(persona))
                                                               .collect(Collectors.toList());
        return outputDTOList;
    }

    @Override
    public PersonaOutputDTO updatePerson(String id,PersonaInputDTO personaInputDTO) {

        Persona persona=mongoTemplate.findById(id,Persona.class);

         if (persona==null){
             throw new PersonNotFoundException("No existe la persona con este id");
         }

             persona.setUsuario(personaInputDTO.getUsuario());
             persona.setPassword(personaInputDTO.getPassword());
             persona.setName(personaInputDTO.getName());
             persona.setSurname(personaInputDTO.getSurname());
             persona.setPersonalEmail(personaInputDTO.getPersonal_email());
             persona.setCompanyEmail(personaInputDTO.getCompany_email());
             persona.setCity(personaInputDTO.getCity());
             persona.setActive(personaInputDTO.isActive());
             persona.setCreatedDate(personaInputDTO.getCreated_date());
             persona.setImageUrl(personaInputDTO.getImagen_url());
             persona.setTerminationDate(personaInputDTO.getTermination_date());
             mongoTemplate.save(persona);
             return convertToDTO(persona);
    }

    @Override
    public void deletePerson(String id) {
        Persona persona=mongoTemplate.findById(id,Persona.class);
        if (persona==null){
            throw new PersonNotFoundException("No existe la persona con este id");
        }
        mongoTemplate.remove(persona);

    }

    private Persona  toPersona(PersonaInputDTO personaInputDTO){
        Persona persona= new Persona();
        persona.setUsuario(personaInputDTO.getUsuario());
        persona.setPassword(personaInputDTO.getPassword());
        persona.setName(personaInputDTO.getName());
        persona.setSurname(personaInputDTO.getSurname());
        persona.setCompanyEmail(personaInputDTO.getCompany_email());
        persona.setPersonalEmail(personaInputDTO.getPersonal_email());
        persona.setActive(personaInputDTO.isActive());
        persona.setCreatedDate(personaInputDTO.getCreated_date());
        persona.setCity(personaInputDTO.getCity());
        persona.setImageUrl(personaInputDTO.getImagen_url());
        persona.setTerminationDate(personaInputDTO.getTermination_date());
        return persona;

    }

    private void validar(PersonaInputDTO personaInputDTO) throws UnprocessableException{
        String usuario= personaInputDTO.getUsuario();
        if (usuario==null) throw new UnprocessableException("Se debe introducir un usuario");
        if (usuario.length()<6 || usuario.length()>10) throw new UnprocessableException("El usuario debe tener entre 6 y 10 carácteres");
        if (personaInputDTO.getPassword()==null) throw new UnprocessableException("Se debe introducir una contraseña");
        if (personaInputDTO.getCreated_date()==null) throw new UnprocessableException("Se debe introducir una fecha");
    }

    private  PersonaOutputDTO convertToDTO(Persona persona){
        PersonaOutputDTO personaoutputDTO= new PersonaOutputDTO();
        personaoutputDTO.setId_persona(persona.getId());
        personaoutputDTO.setUsuario(persona.getUsuario());
        personaoutputDTO.setCity(persona.getCity());
        personaoutputDTO.setPersonal_email(persona.getPersonalEmail());
        personaoutputDTO.setActive(persona.getActive());
        personaoutputDTO.setCreated_date(persona.getCreatedDate());
        personaoutputDTO.setSurname(persona.getSurname());
        personaoutputDTO.setName(persona.getName());
        personaoutputDTO.setCompany_email(persona.getCompanyEmail());
        personaoutputDTO.setImagen_url(persona.getImageUrl());
        personaoutputDTO.setTermination_date(persona.getTerminationDate());
        return personaoutputDTO;
    }


}
