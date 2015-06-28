package com.main.model.billing;

import com.main.fw.model.AbstractEntity;
import com.main.model.user.User;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * sadra
 * Created by sadra on 12/21/14.
 */
@Entity
@Component
@Table(name="account")
public class Account extends AbstractEntity {

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private float payment_amount;
    private float account_due;
    private Date payment_date;

    public User getUser() {
        return user;
    }

    public Account setUser(User user) {
        this.user = user;
        return this;
    }

    public float getPayment_amount() {
        return payment_amount;
    }

    public Account setPayment_amount(float payment_amount) {
        this.payment_amount = payment_amount;
        return this;
    }

    public float getAccount_due() {
        return account_due;
    }

    public Account setAccount_due(float account_due) {
        this.account_due = account_due;
        return this;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public Account setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
        return this;
    }

    @Override
    public String toString() {
        return "Account{" +
                "customer=" + user +
                ", payment_amount=" + payment_amount +
                ", account_due=" + account_due +
                ", payment_date=" + payment_date +
                '}';
    }
}
