package com.reba.api.person.repository;

import com.reba.api.person.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
