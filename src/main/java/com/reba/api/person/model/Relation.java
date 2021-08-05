package com.reba.api.person.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.reba.api.person.enums.RelationType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "relations")
public class Relation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable  = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Person person;

    private Integer relatedPersonId;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private RelationType relation;

    public Relation() {}

    public Relation(Integer id, Person person, Integer relatedPersonId, RelationType relation) {
        this.id = id;
        this.person = person;
        this.relatedPersonId = relatedPersonId;
        this.relation = relation;
    }

    public Relation(Person person, Integer relatedPersonId, RelationType relation) {
        this.person = person;
        this.relatedPersonId = relatedPersonId;
        this.relation = relation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getRelatedPersonId() {
        return relatedPersonId;
    }

    public void setRelatedPersonId(Integer relatedPersonId) {
        this.relatedPersonId = relatedPersonId;
    }

    public RelationType getRelation() {
        return relation;
    }

    public void setRelation(RelationType relation) {
        this.relation = relation;
    }
}
