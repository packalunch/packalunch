package com.curry.model;

import com.curry.fw.model.AbstractEntity;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * CurryWithAri
 * Created by sadra on 10/30/14.
 */
@Entity
@Component
@Table(name="customer")
public class Customer extends AbstractEntity {

    private String first_name;
    private String last_name;
    private String address;
    private String telephone;

    @OneToOne (mappedBy = "customer", cascade= CascadeType.ALL)
    private Account account;

    public String getFirst_name() {
        return first_name;
    }

    public Customer setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public Customer setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Customer setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public Customer setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public Customer setAccount(Account account) {
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
}