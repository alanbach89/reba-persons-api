package com.reba.api.person.service;

import com.reba.api.person.enums.DocumentType;
import com.reba.api.person.exception.AdultPersonException;
import com.reba.api.person.exception.AtLeastOneContactDataException;
import com.reba.api.person.exception.PersonNotFoundException;
import com.reba.api.person.help.CountryList;
import com.reba.api.person.model.Person;
import com.reba.api.person.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class PersonServiceUnitTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    private List<String> countryNames = CountryList.getCountryNames();

    @BeforeEach
    void setUp() {
        personRepository = Mockito.mock(PersonRepository.class);
        personService = new PersonServiceImpl(personRepository);
    }

    @Test
    void whenSavePersonShouldReturnThatPerson() throws AtLeastOneContactDataException, AdultPersonException {

        DocumentType dni = DocumentType.DNI;
        Person person = new Person("Jose", "Manolo", 31, dni, "34345345", countryNames.get(1), "324554423", "asdasd@gmail.com");
        when(personRepository.save(any(Person.class))).thenReturn(person);
        Person personCreated = personService.create(person);

        assertEquals(personCreated.getLastName(), person.getLastName());
        verify(personRepository).save(person);
    }

    @Test
    public void whenGetByIdErrorShouldReturnException() throws Exception {

        when(personRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));

        assertThrows(PersonNotFoundException.class, () -> {
            personService.getById(999);
        });

        verify(personRepository).findById(anyInt());
    }

    @Test
    public void whenGetByFullNameAndCountryErrorShouldReturnException() throws Exception {

        when(personRepository.findByFullNameAndCounty(anyString(), anyString(), anyString())).thenReturn(null);

        assertThrows(PersonNotFoundException.class, () -> {
            personService.getByFullNameAndCounty("asd", "asd", "asd");
        });

        verify(personRepository).findByFullNameAndCounty(anyString(), anyString(), anyString());
    }
}