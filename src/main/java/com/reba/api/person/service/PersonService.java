package com.reba.api.person.service;

import com.reba.api.person.model.Person;
import com.reba.api.person.model.PersonsByCountry;

import java.util.List;

public interface PersonService {

    Person create(Person person);
    Person update(Person person);
    void delete(Long personId);
    Person getById(Long personId);
    Person getByFullNameAndCounty(String firstName, String lastName, String country);
    List<Person> getAll();
    List<PersonsByCountry> getPersonCountByCounty();
}
