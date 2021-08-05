package com.reba.api.person.service;

import com.reba.api.person.exception.PersonNotFoundException;
import com.reba.api.person.model.Person;
import com.reba.api.person.model.PersonsByCountry;
import com.reba.api.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }
    @Override
    public Person update(Person person) {
        personRepository.findById(person.getId())
                .orElseThrow(() -> new PersonNotFoundException(person.getId()));
        return personRepository.save(person);
    }

    @Override
    public void delete(Long personId) {

        personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));

        personRepository.deleteById(personId);
    }

    @Override
    public Person getById(Long personId) {

        return personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));
    }


    public Person getByFullNameAndCounty(String firstName, String lastName, String country) {

        return  Optional.ofNullable(personRepository.findByFullNameAndCounty(firstName, lastName, country))
                .orElseThrow(() -> new PersonNotFoundException());
    }

    @Override
    public List<Person> getAll() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public List<PersonsByCountry> getPersonCountByCounty() {
        Optional opt = Optional.of(personRepository.getPersonCountByCounty());
        List<PersonsByCountry> countlist = new ArrayList<>();

                if(opt.isPresent()) {
                    List<Object[]> repoList = (List<Object[]>) opt.get();
                    for (Object[] ob : repoList){
                        countlist.add(new PersonsByCountry((String) ob[0], (Integer) ob[1]));
                    }
                    return countlist;
                } else throw new PersonNotFoundException();
    }
}
