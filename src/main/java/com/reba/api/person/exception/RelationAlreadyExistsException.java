package com.reba.api.person.exception;

import javax.persistence.EntityExistsException;

public class RelationAlreadyExistsException extends EntityExistsException {
    public RelationAlreadyExistsException() {
        super("Ya existe una relacion para esas personas");
    }
}
