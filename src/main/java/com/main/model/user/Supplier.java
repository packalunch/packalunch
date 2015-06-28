package com.main.model.user;

import com.main.model.product.Plan;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sadra on 6/10/15.
 */
@Entity
@DiscriminatorValue("supplier")
public class Supplier extends User{

    public Supplier() {
        super.userType = "supplier";
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade= CascadeType.ALL)
    private List <Plan> planList;

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }
}
