package com.reba.api.person.service;

import com.reba.api.person.model.Person;
import com.reba.api.person.dto.PersonsByCountry;

import java.util.List;

public interface PersonService {

    Person create(Person person);
    Person update(Person person);
    void delete(Integer personId);
    Person getById(Integer personId);
    Person getByFullNameAndCounty(String documentType, String documentNumber, String country);
    List<Person> getAll();
    List<PersonsByCountry> getPersonCountByCounty();
}
