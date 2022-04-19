package com.company.models;


import java.sql.Date;

public class Abonent {
    private int id;
    private String firstName, lastName, patronimyc, email;
    private Date birthDate;

    public Abonent(String firstName, String lastName, String patronimyc, String email, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronimyc = patronimyc;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Abonent(int id, String firstName, String lastName, String patronimyc, String email, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronimyc = patronimyc;
        this.email = email;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPatronimyc() {
        return patronimyc;
    }

    public void setPatronimyc(String patronimyc) {
        this.patronimyc = patronimyc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Abonent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronimyc='" + patronimyc + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
