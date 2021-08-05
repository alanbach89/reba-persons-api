package com.reba.api.person.exception;

import javax.persistence.EntityNotFoundException;

public class RelationNotFoundException extends EntityNotFoundException {
    public RelationNotFoundException(Integer relationId) {
        super(String.format("Relacion con id %d no encontrada", relationId));
    }

    public RelationNotFoundException() {
        super();
    }
}
