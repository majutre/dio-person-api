package com.digitalinnovationone.personrestapi.service;

import com.digitalinnovationone.personrestapi.dto.request.PersonDTO;
import com.digitalinnovationone.personrestapi.dto.response.MessageResponseDTO;
import com.digitalinnovationone.personrestapi.entity.Person;
import com.digitalinnovationone.personrestapi.mapper.PersonMapper;
import com.digitalinnovationone.personrestapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO){
        Person personToCreate = personMapper.toModel(personDTO);

        Person newPerson = personRepository.save(personToCreate);
        return MessageResponseDTO
                .builder()
                .message("Created new person with ID " + newPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> all = personRepository.findAll();
        return all.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
}