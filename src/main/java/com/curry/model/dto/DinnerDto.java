package com.curry.model.dto;

import com.curry.plugins.date.Day;

import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 12/9/14.
 */
public class DinnerDto {

    private int id;
    private String firstName;
    private String lastName;
    private List<Day> dinerSchedule;
    private int balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Day> getDinerSchedule() {
        return dinerSchedule;
    }

    public void setDinerSchedule(List<Day> dinerSchedule) {
        this.dinerSchedule = dinerSchedule;
    }

    @Override
    public String toString() {
        return "DinnerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dinerSchedule=" + dinerSchedule +
                '}';
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}
