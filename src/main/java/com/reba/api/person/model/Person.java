package com.reba.api.person.model;

import com.reba.api.person.enums.DocumentType;
import com.reba.api.person.exception.AdultPersonException;
import com.reba.api.person.exception.AtLeastOneContactDataException;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "persons")
@IdClass(PersonId.class)
public class Person {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private Long id;


    @NotBlank
    @NotNull
    @Column(name="first_name", length = 150)
    private String firstName;

    @NotBlank
    @NotNull
    @Column(name="last_name", length = 150)
    private String lastName;

    @Id
    @NotBlank
    @NotNull
    @Column(name="document_type", columnDefinition = "VARCHAR(45)")
    @Length(max = 45)
    @Enumerated(value = EnumType.STRING)
    private DocumentType documentType;

    @Id
    @NotBlank
    @NotNull
    @Column(name="document_number", length = 45)
    private String documentNumber;

    @Id
    @NotBlank
    @NotNull
    @Column(length = 45)
    private String country;

    @Column(length = 45)
    private String phone;

    @Column(length = 155)
    private String email;

    @OneToMany(mappedBy ="person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Relation> relations;

    @NotBlank
    @NotNull
    private Integer age;

    public Person(String firstName, String lastName, Integer age, DocumentType documentType, String documentNumber, String country, String phone, String email) throws AtLeastOneContactDataException, AdultPersonException {
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
        if (age < 18)
            throw new AdultPersonException();
    }

    private void validateAtLeastOneContactData() throws AtLeastOneContactDataException {
        if ((phone == null || "".equals(phone)) && (email == null || "".equals(email)))
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

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
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
