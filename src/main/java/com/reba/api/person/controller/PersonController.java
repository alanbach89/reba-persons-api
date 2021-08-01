package com.reba.api.person.controller;

import com.reba.api.person.model.Person;
import com.reba.api.person.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public List<Person> getPersons() {
        return personService.getAll();
    }

    @GetMapping("/")
    public Person getPersonById(@RequestParam("id") String personId) {
        return personService.getById(Integer.getInteger(personId));
    }
}
