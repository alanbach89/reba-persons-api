package com.reba.api.person.service;

import com.reba.api.person.exception.RelationNotFoundException;
import com.reba.api.person.model.Relation;
import com.reba.api.person.repository.RelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {

    private RelationRepository relationRepository;

    @Autowired
    public RelationServiceImpl(RelationRepository relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public Relation create(Relation relation) {
        return relationRepository.save(relation);
    }

    @Override
    public Relation update(Relation relation) {
        relationRepository.findById(relation.getId())
                .orElseThrow(() -> new RelationNotFoundException(relation.getId()));
        return relationRepository.save(relation);
    }

    @Override
    public void delete(Integer relationId) {

        relationRepository.findById(relationId)
                .orElseThrow(() -> new RelationNotFoundException(relationId));

        relationRepository.deleteById(relationId);
    }

    @Override
    public Relation getById(Integer relationId) {

        return relationRepository.findById(relationId)
                .orElseThrow(() -> new RelationNotFoundException(relationId));
    }

    @Override
    public List<Relation> getAll() {
        return (List<Relation>) relationRepository.findAll();
    }
}
