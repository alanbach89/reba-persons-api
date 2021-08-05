package com.reba.api.person.controller;

import com.reba.api.person.dto.PersonsByCountry;
import com.reba.api.person.enums.RelationType;
import com.reba.api.person.exception.PersonAlreadyExistsException;
import com.reba.api.person.exception.PersonNotFoundException;
import com.reba.api.person.model.Person;
import com.reba.api.person.model.Relation;
import com.reba.api.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getPersons() {
        try {
            List<Person> list = personService.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Integer personId) {
        try {
            Person person = personService.getById(personId);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        try {
            validateNonRepeatedData(person.getDocumentType().toString(), person.getDocumentNumber(), person.getCountry());
            Person updatedPerson =  personService.update(person);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        } catch (PersonAlreadyExistsException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        try {
            validateNonRepeatedData(person.getDocumentType().toString(), person.getDocumentNumber(), person.getCountry());
            Person createPerson =  personService.create(person);
            return new ResponseEntity<>(createPerson, HttpStatus.OK);
        } catch (PersonAlreadyExistsException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<List<PersonsByCountry>> getPersonCountByCountry() {
        try {
            List<PersonsByCountry> stats =  personService.getPersonCountByCounty();
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id1}/padre/{id2}")
    public ResponseEntity<String> updateParentRelationship(@PathVariable("id1") Integer personId1, @PathVariable("id2") Integer personId2) {
        try {
            Person person1 = personService.getById(personId1);
            Person person2 = personService.getById(personId2);
            person1.getRelations().add(new Relation(person1, personId2, RelationType.PADRE));
            personService.update(person1);

            return new ResponseEntity<>(personId1 + " es padre de " + personId2, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateNonRepeatedData(String documentType, String docunmentValue, String country) {
        try {
            Person person = personService.getByFullNameAndCounty(documentType, docunmentValue, country);
            if (person != null)
                throw new PersonAlreadyExistsException();
        } catch (PersonNotFoundException ex) {}
    }
}
