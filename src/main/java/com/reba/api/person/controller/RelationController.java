package com.reba.api.person.controller;

import com.reba.api.person.dto.PersonsByCountry;
import com.reba.api.person.enums.RelationType;
import com.reba.api.person.exception.RelationAlreadyExistsException;
import com.reba.api.person.exception.RelationNotFoundException;
import com.reba.api.person.model.Person;
import com.reba.api.person.model.Relation;
import com.reba.api.person.repository.RelationRepository;
import com.reba.api.person.service.PersonService;
import com.reba.api.person.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relaciones")
public class RelationController {

    private PersonService personService;

    private RelationService relationService;

    @Autowired
    public RelationController(PersonService personService, RelationService relationService) {
        this.personService = personService;
        this.relationService = relationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relation> getPersonRelationship(@PathVariable("id") Integer relationId) {
        try {
            Relation relation = relationService.getById(relationId);

            return new ResponseEntity<>(relation, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<String> getPersonRelationship(@PathVariable("id1") Integer personId1, @PathVariable("id2") Integer personId2) {
        try {
            Person person1 = personService.getById(personId1);

            Relation relationship = person1.getRelations().stream().filter(relation -> relation.getRelatedPersonId() == personId2)
                    .findFirst().orElseThrow(() -> new RelationNotFoundException());

            return new ResponseEntity<>(personId1 + " es " + relationship.getRelation() + " de " + personId2, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id1}/{relation}/{id2}")
    public ResponseEntity<Relation> createPersonRelationship(@PathVariable("id1") Integer personId1, @PathVariable("relation") String relation, @PathVariable("id2") Integer personId2) {
        try {

            Person person1 = personService.getById(personId1);
            Person person2 = personService.getById(personId2);

            Boolean relationship1 = person1.getRelations().stream().filter(rel -> rel.getRelatedPersonId() == personId2).findFirst().isPresent();

            if(!relationship1) {
                Relation createdRelation = new Relation(person1, person2.getId(), RelationType.fromString(relation));
                person1.getRelations().add(createdRelation);
                personService.update(person1);
                return new ResponseEntity<>(createdRelation, HttpStatus.OK);
            } else throw new RelationAlreadyExistsException();

        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
