package com.main.model.dto;

/**
 * PackALunch
 * Created by sadra on 12/17/14.
 */
public class UserDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private String email;
    private CredentialDto credentialDto;

    public Integer getId() {
        return id;
    }

    public UserDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public UserDto setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public CredentialDto getCredentialDto() {
        return credentialDto;
    }

    public UserDto setCredentialDto(CredentialDto credentialDto) {
        this.credentialDto = credentialDto;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }
}
