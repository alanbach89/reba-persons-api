package com.reba.api.person.model;

import com.reba.api.person.enums.DocumentType;
import com.reba.api.person.exception.AdultPersonException;
import com.reba.api.person.exception.AtLeastOneContactDataException;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Objects;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private DocumentType documentType;

    @NotBlank
    private String documentNumber;

    @NotBlank
    private String country;

    private String phone;

    private String email;

    @NotBlank
    private Integer age;

    public Person(Long id, String firstName, String lastName, DocumentType documentType, String documentNumber, String country, String phone, String email, Integer age) throws AtLeastOneContactDataException, AdultPersonException {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.age = age;

        this.validateAgeIsAtLeastEighteen();
        this.validateAtLeastOneContactData();
    }

    private void validateAgeIsAtLeastEighteen() throws AdultPersonException {
        if (age >= 18)
            throw new AdultPersonException();
    }

    private void validateAtLeastOneContactData() throws AtLeastOneContactDataException {
        if ((phone != null && !phone.equals("")) || (email != null && !email.equals("")))
            throw new AtLeastOneContactDataException();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getDocumentType() == person.getDocumentType() &&
                getDocumentNumber().equals(person.getDocumentNumber()) &&
                getCountry().equals(person.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocumentType(), getDocumentNumber(), getCountry());
    }
}
