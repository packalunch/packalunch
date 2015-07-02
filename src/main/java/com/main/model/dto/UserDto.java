package com.main.model.dto;

/**
 * PackALunch
 * Created by sadra on 12/17/14.
 */
public class UserDto {

    private Integer id;
    private String first_name;
    private String last_name;
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

    public String getFirst_name() {
        return first_name;
    }

    public UserDto setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public UserDto setLast_name(String last_name) {
        this.last_name = last_name;
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
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
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
