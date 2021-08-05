package com.reba.api.person.model;

public class PersonsByCountry {

    private String country;

    private Integer personsCount;

    public PersonsByCountry(String country, Integer personsCount) {
        this.country = country;
        this.personsCount = personsCount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPersonsCount() {
        return personsCount;
    }

    public void setPersonsCount(Integer personsCount) {
        this.personsCount = personsCount;
    }
}
