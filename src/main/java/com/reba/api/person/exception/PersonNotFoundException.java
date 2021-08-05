package com.reba.api.person.exception;

import javax.persistence.EntityNotFoundException;

public class PersonNotFoundException extends EntityNotFoundException {
    public PersonNotFoundException(Long personId) {
        super(String.format("Persona con id %d no encontrada", personId));
    }

    public PersonNotFoundException() {
        super();
    }
}
