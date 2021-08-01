package com.reba.api.person.service;

import com.reba.api.person.model.Person;
import com.reba.api.person.repository.PersonRepository;
import com.reba.api.person.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public Integer delete(Integer personId) {
        return null;
    }

    @Override
    public Person getById(Integer personId) {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }
}
