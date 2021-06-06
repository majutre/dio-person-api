package com.digitalinnovationone.personrestapi.service;

import com.digitalinnovationone.personrestapi.dto.request.PersonDTO;
import com.digitalinnovationone.personrestapi.dto.response.MessageResponseDTO;
import com.digitalinnovationone.personrestapi.entity.Person;
import com.digitalinnovationone.personrestapi.exception.PersonNotFoundException;
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
        return createMessageResponse(newPerson.getId(),"Created new person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> all = personRepository.findAll();
        return all.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = checkIfPersonExists(id);
//        Optional<Person> optPerson = personRepository.findById(id);

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        checkIfPersonExists(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        checkIfPersonExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);

        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }

    private Person checkIfPersonExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String msg) {
        return MessageResponseDTO
                .builder()
                .message(msg + id)
                .build();
    }
}