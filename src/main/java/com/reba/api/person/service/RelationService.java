package com.reba.api.person.service;

import com.reba.api.person.model.Relation;

import java.util.List;

public interface RelationService {

    Relation create(Relation relation);
    Relation update(Relation relation);
    void delete(Integer relationId);
    Relation getById(Integer relationId);
    List<Relation> getAll();
}
