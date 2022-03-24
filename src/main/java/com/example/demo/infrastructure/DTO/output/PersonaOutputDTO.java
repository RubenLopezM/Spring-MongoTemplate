package com.example.demo.infrastructure.DTO.output;

import com.example.demo.Domain.Persona;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class PersonaOutputDTO {
    private String id_persona;
    private String usuario;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public PersonaOutputDTO(Persona persona){
        if (persona==null){
            return;
        }
        setId_persona(persona.getId());
        setUsuario(persona.getUsuario());
        setName(persona.getName());
        setSurname(persona.getSurname());
        setCompany_email(persona.getCompanyEmail());
        setPersonal_email(persona.getPersonalEmail());
        setCity(persona.getCity());
        setActive(persona.getActive());
        setCreated_date(persona.getCreatedDate());
        setImagen_url(persona.getImageUrl());
        setTermination_date(persona.getTerminationDate());
    }
}
