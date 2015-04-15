package com.main.model.dto;

import java.util.Date;

/**
 * PackALunch
 * Created by sadra on 12/21/14.
 */
public class AccountDto {

    private int id;
    private float payment_amount;
    private float account_due;
    private Date payment_date;

    public int getId() {
        return id;
    }

    public AccountDto setId(int id) {
        this.id = id;
        return this;
    }

    public float getPayment_amount() {
        return payment_amount;
    }

    public AccountDto setPayment_amount(float payment_amount) {
        this.payment_amount = payment_amount;
        return this;
    }

    public float getAccount_due() {
        return account_due;
    }

    public AccountDto setAccount_due(float account_due) {
        this.account_due = account_due;
        return this;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public AccountDto setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
        return this;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", payment_amount=" + payment_amount +
                ", account_due=" + account_due +
                ", payment_date=" + payment_date +
                '}';
    }
}
