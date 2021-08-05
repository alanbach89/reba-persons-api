package com.reba.api.person.repository;

import com.reba.api.person.model.Person;
import com.reba.api.person.model.Relation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RelationRepository extends CrudRepository<Relation, Long> {
}
