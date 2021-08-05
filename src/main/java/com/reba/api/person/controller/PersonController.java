package com.reba.api.person.controller;

import com.reba.api.person.enums.RelationType;
import com.reba.api.person.model.Person;
import com.reba.api.person.model.PersonsByCountry;
import com.reba.api.person.model.Relation;
import com.reba.api.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
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
        List<Person> list = personService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Person> getPersonById(@RequestParam("id") Long personId) {
        Person person = personService.getById(personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        Person updatedPerson =  personService.update(person);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createPerson =  personService.create(person);
        return new ResponseEntity<>(createPerson, HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity<List<PersonsByCountry>> getPersonCountByCountry() {
        List<PersonsByCountry> stats =  personService.getPersonCountByCounty();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @PostMapping("/{id1}/padre/{id2}")
    public ResponseEntity<String> updateParentRelationship(@RequestParam("id1") Long personId1, @RequestParam("id2") Long personId2) {
        Person person1 = personService.getById(personId1);
        Person person2 = personService.getById(personId2);

        Relation p1IsParent = new Relation(person1, RelationType.PADRE);
        person2.getRelations().add(p1IsParent);
        personService.update(person2);

        return new ResponseEntity<>(personId1 + " es padre de " + personId2, HttpStatus.OK);
    }

    @GetMapping("/relaciones/{id1}/{id2}")
    public ResponseEntity<String> getPersonRelationship(@RequestParam("id1") Long personId1, @RequestParam("id2") Long personId2) {
        Person person1 = personService.getById(personId1);

        Relation relationship = person1.getRelations().stream().filter(relation -> relation.getPerson().getId() == personId2).findFirst().get();


        return new ResponseEntity<>(personId1 + " es " + relationship.getRelation() + " de " + personId2, HttpStatus.OK);
    }

    @PostMapping("/relaciones/{id1}/{relation}/{id2}")
    public ResponseEntity<Person> updatePersonRelationship(@RequestParam("id1") Long personId1, @RequestParam("relation") String relation, @RequestParam("id2") Long personId2) {
        Person person1 = personService.getById(personId1);
        Person person2 = personService.getById(personId2);

        Relation p1IsParent = new Relation(person1, RelationType.fromString(relation));

        if(person2.getRelations() != null)
            person2.getRelations().add(p1IsParent);
        else
            person2.setRelations(List.of(p1IsParent));

        Person updatedPerson = personService.update(person2);

        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }
}
