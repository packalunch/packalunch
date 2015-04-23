package com.main.model.dto;

/**
 * PackALunch
 * Created by sadra on 12/21/14.
 */
public class CredentialDto {

    private String salt;
    private String password;
    private String role;
    private String  signInProvider;

    public String getSalt() {
        return salt;
    }

    public CredentialDto setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CredentialDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public CredentialDto setRole(String role) {
        this.role = role;
        return this;
    }

    public String getSignInProvider() {
        return signInProvider;
    }

    public CredentialDto setSignInProvider(String signInProvider) {
        this.signInProvider = signInProvider;
        return this;
    }
}
