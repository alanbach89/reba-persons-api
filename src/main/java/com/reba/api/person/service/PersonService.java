package com.reba.api.person.service;

import com.reba.api.person.model.Person;

import java.util.List;

public interface PersonService {

    Person save(Person person);
    Integer delete(Integer personId);
    Person getById(Integer personId);
    List<Person> getAll();
}
