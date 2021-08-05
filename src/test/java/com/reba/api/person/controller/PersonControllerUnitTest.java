package com.reba.api.person.controller;

import com.reba.api.person.enums.DocumentType;
import com.reba.api.person.exception.AdultPersonException;
import com.reba.api.person.exception.AtLeastOneContactDataException;
import com.reba.api.person.help.CountryList;
import com.reba.api.person.model.Person;
import com.reba.api.person.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * ● No puede haber personas repetidas (las personas se identifican por Tipo de
 * documento, número de documento, país).
 * ● Las personas deben tener al menos un dato de contacto.
 * ● No pueden crearse personas menores de 18 años.
 * ● Pueden tener cualquier nacionalidad.
 * ● Las entidades complementarias a Personas deben estar cargadas al
 * momento de usar la API.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class PersonControllerUnitTest {

    @Autowired
    PersonController personController;

    @Autowired
    private PersonService personService;

    private List<String> countryNames = CountryList.getCountryNames();

    @BeforeEach
    void setUp() {
        personService = Mockito.mock(PersonService.class);
        personController = new PersonController(personService);
    }

    @Test
    void validateUniquePerson() {
        List<Person> persons = new ArrayList<>();
        Person repeatedPerson ;
        Person notRepeatedPerson ;
        when(personService.getAll()).thenReturn(persons);
        try {

            DocumentType dni = DocumentType.DNI;
            persons.add(new Person("Alan", "Bach", 31, dni, "34997678", countryNames.get(0), "789456123", "alanbach89@gmail.com"));
            persons.add(new Person("Jose", "Manolo", 31, dni, "34345345", countryNames.get(1), "324554423", null));
            persons.add(new Person("Ramon", "Ruiz", 31, dni, "349974444", countryNames.get(0), null, "rruiz@gmail.com"));
            persons.add(new Person("Sebastian", "Gonzalez", 31, dni, "35679768", countryNames.get(2), "089456654", "lolo123@gmail.com"));

            repeatedPerson = new Person("Alan", "Bach", 31, dni, "34997678", countryNames.get(0), "789456123", "alanbach89@gmail.com");
            notRepeatedPerson = new Person("Sebastian", "Gonzalez", 31, dni, "35679768", countryNames.get(0), "089456654", "lolo123@gmail.com");


            assertEquals(true, personController.getPersons().getBody().contains(repeatedPerson));
            assertEquals(false, personController.getPersons().getBody().contains(notRepeatedPerson));
        } catch (Exception ex) {
            System.out.println("Datos de persona invalidos");
        }
    }

    @Test
    void validateAtLeastOneContactDataOptionException() {

        DocumentType dni = DocumentType.DNI;
        assertThrows(AtLeastOneContactDataException.class,
                () -> new Person("Ramon", "Ruiz", 31, dni, "349974444", countryNames.get(0), null, null));
    }

    @Test
    void validatePersonWithAgeEighteenOrMore() {

        DocumentType dni = DocumentType.DNI;
        assertThrows(AdultPersonException.class,
            () -> new Person("Ramon", "Ruiz", 16, dni, "349974444", countryNames.get(0), "123543553", null));
    }
}