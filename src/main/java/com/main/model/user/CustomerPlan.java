package com.main.model.user;

import com.main.fw.model.AbstractEntity;
import com.main.model.product.Plan;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sadra on 6/10/15.
 */
@Entity
@Component
@Table(name="customer_plan")
public class CustomerPlan extends AbstractEntity {

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user; //customer

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="plan_id")
    private Plan plan;

    private Integer credit;

    public User getUser() {
        return user;
    }

    public CustomerPlan setUser(User user) {
        this.user = user;
        return this;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}
