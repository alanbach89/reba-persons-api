package com.reba.api.person.exception;

import javax.persistence.EntityExistsException;

public class PersonAlreadyExistsException extends EntityExistsException {

    public PersonAlreadyExistsException() {
        super("Ya existe una persona con ese documento y pais");
    }
}
