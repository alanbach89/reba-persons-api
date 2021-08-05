package com.reba.api.person.model;

import com.reba.api.person.enums.DocumentType;

import java.io.Serializable;
import java.util.Objects;

public class PersonId implements Serializable {


    private DocumentType documentType;

    private String documentNumber;

    private String country;

    public PersonId(DocumentType documentType, String documentNumber, String country) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonId)) return false;
        PersonId personId = (PersonId) o;
        return documentType == personId.documentType &&
                documentNumber.equals(personId.documentNumber) &&
                country.equals(personId.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentType, documentNumber, country);
    }
}
