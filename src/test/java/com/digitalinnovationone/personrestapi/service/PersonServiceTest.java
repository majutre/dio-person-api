package com.digitalinnovationone.personrestapi.service;

import com.digitalinnovationone.personrestapi.dto.request.PersonDTO;
import com.digitalinnovationone.personrestapi.dto.response.MessageResponseDTO;
import com.digitalinnovationone.personrestapi.entity.Person;
import com.digitalinnovationone.personrestapi.repository.PersonRepository;
import com.digitalinnovationone.personrestapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.digitalinnovationone.personrestapi.utils.PersonUtils.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    
    @Mock
    private PersonRepository personRepository;
    
    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedNewPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedNewPerson);
        MessageResponseDTO expectedSuccessMessage = MessageResponseDTO.builder().message("Created new person with ID " + expectedNewPerson.getId()).build();
        MessageResponseDTO successMessage = personService.createPerson(personDTO);

        Assertions.assertEquals(expectedSuccessMessage, successMessage);
    }
}
