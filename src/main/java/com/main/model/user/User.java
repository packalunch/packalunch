package com.main.model.user;

import com.main.fw.model.AbstractEntity;
import com.main.model.billing.Account;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * PackALunch
 * Created by sadra on 10/30/14.
 */
@Entity
@Component
@Table(name="user")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.STRING)
public class User extends AbstractEntity {

    private String first_name;
    private String last_name;

    @Column(name = "email", length = 100,  unique = true)
    private String email;

    @Column(name="user_type", nullable=false, length=8, insertable = false, updatable = false)
    protected String userType;

    private String address;
    private String telephone;

    @OneToOne (mappedBy = "user", cascade = CascadeType.ALL)
    private Credential credential;

    @OneToOne (mappedBy = "user", cascade= CascadeType.ALL)
    private Account account;

    public Credential getCredential() {
        return credential;
    }

    public User setCredential(Credential credential) {
        this.credential = credential;
        return this;
    }

    public String getFirst_name() {
        return first_name;
    }

    public User setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public User setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }


    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public User setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public User setAccount(Account account) {
        this.account = account;
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + super.getId() +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public String getUserType() {
        return userType;
    }

    public User setUserType(String userType) {
        this.userType = userType;
        return this;
    }

}