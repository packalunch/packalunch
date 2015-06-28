package com.main.model.product;

import com.main.fw.model.AbstractEntity;
import com.main.model.user.User;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sadra on 6/10/15.
 */
@Entity
@Component
@Table(name="plan")
public class Plan extends AbstractEntity {

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user; //supplier

    @Column (name = "number_of_meals")
    private Integer numberOfMeals;

    @Column (name = "unit_price")
    private Double unitPrice;

    private String name, description;

    private boolean isAvailable;

    public User getUser() {
        return user;
    }

    public Plan setUser(User user) {
        this.user = user;
        return this;
    }

    public Integer getNumberOfMeals() {
        return numberOfMeals;
    }

    public Plan setNumberOfMeals(Integer numberOfMeals) {
        this.numberOfMeals = numberOfMeals;
        return this;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Plan setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public String getName() {
        return name;
    }

    public Plan setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Plan setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Plan setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + super.getId() +
                ", numberOfMeals=" + numberOfMeals +
                ", unitPrice=" + unitPrice +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
