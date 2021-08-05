package com.reba.api.person.model;

import com.reba.api.person.enums.RelationType;

import javax.persistence.*;

@Entity
@Table(name = "relations")
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumns({
            @JoinColumn(name = "documentType", insertable = false, updatable = false),
            @JoinColumn(name = "documentNumber", insertable = false, updatable = false),
            @JoinColumn(name = "country", insertable = false, updatable = false)
    })
    private Person person;

    private RelationType relation;

    public Relation(Person person, RelationType relation) {
        this.person = person;
        this.relation = relation;
    }

    public Relation(Long id, Person person, RelationType relation) {
        this.id = id;
        this.person = person;
        this.relation = relation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public RelationType getRelation() {
        return relation;
    }

    public void setRelation(RelationType relation) {
        this.relation = relation;
    }
}
