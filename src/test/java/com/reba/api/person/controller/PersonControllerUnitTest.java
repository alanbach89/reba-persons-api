package com.reba.api.person.controller;

import com.reba.api.person.repository.PersonRepository;
import com.reba.api.person.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

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

    PersonController personController;

    @Autowired
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personController = new PersonController(personService);
    }

    @Test
    void validateUniquePerson() {
    }

    @Test
    void validateAtLeastOneContactDataOption() {
    }

    @Test
    void validatePersonWithAgeEighteenOrMore() {
    }
}