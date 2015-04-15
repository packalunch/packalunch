package com.main.model;

import com.main.fw.model.AbstractEntity;
import com.main.model.dto.Role;
import com.main.model.dto.SocialMediaService;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * PackALunch
 * Created by sadra on 01/2/15.
 */
@Entity
@Component
@Table(name="credential")
public class Credential extends AbstractEntity {

    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Column(name = "salt")
    private String salt;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMediaService signInProvider;

    public Customer getCustomer() {
        return customer;
    }

    public Credential setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public Credential setRole(Role role) {
        this.role = role;
        return this;
    }

    public SocialMediaService getSignInProvider() {
        return signInProvider;
    }

    public Credential setSignInProvider(SocialMediaService signInProvider) {
        this.signInProvider = signInProvider;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public Credential setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Credential setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Credential{" +
                ", salt='" + salt + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", signInProvider=" + signInProvider +
                '}';
    }
}