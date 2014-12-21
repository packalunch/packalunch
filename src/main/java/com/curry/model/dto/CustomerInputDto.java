package com.curry.model.dto;

/**
 * CurryWithAri
 * Created by sadra on 12/10/14.
 */
public class CustomerInputDto {
    private String firstName;
    private String lastName;
    private int id;

    public CustomerInputDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CustomerInputDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerInputDto setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CustomerInputDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }
}
