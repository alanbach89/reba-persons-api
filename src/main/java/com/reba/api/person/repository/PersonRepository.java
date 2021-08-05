package com.reba.api.person.repository;

import com.reba.api.person.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query(value = "SELECT * FROM person WHERE first_Name = ?1 AND last_name = ?2 AND country = ?3", nativeQuery = true)
    Person findByFullNameAndCounty(String lastName, String firstName, String country);


    @Query(value = "SELECT country, count(id) FROM person GROUP BY country", nativeQuery = true)
    List<Object[]> getPersonCountByCounty();
}
