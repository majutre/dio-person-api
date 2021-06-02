package com.digitalinnovationone.personrestapi.service;

import com.digitalinnovationone.personrestapi.dto.request.PersonDTO;
import com.digitalinnovationone.personrestapi.dto.response.MessageResponseDTO;
import com.digitalinnovationone.personrestapi.entity.Person;
import com.digitalinnovationone.personrestapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO){
        Person pe

        Person newPerson = personRepository.save(personDTO);
        return MessageResponseDTO
                .builder()
                .message("Created new person with ID " + newPerson.getId())
                .build();
    }
}