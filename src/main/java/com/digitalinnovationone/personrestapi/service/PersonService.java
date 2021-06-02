package com.digitalinnovationone.personrestapi.service;

import com.digitalinnovationone.personrestapi.dto.MessageResponseDTO;
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

    public MessageResponseDTO createPerson(@RequestBody Person person){
        Person newPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created new person with ID " + newPerson.getId())
                .build();
    }
}