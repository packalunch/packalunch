package com.main.model.dto;

/**
 * PackALunch
 * Created by sadra on 12/17/14.
 */
public class CustomerDto {

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

    public CustomerDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirst_name() {
        return first_name;
    }

    public CustomerDto setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public CustomerDto setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CustomerDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public CustomerDto setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public CredentialDto getCredentialDto() {
        return credentialDto;
    }

    public CustomerDto setCredentialDto(CredentialDto credentialDto) {
        this.credentialDto = credentialDto;
        return this;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public CustomerDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }
}
