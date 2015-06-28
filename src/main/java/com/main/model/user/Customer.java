package com.main.model.user;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Sadra on 6/10/15.
 */
@Entity
@DiscriminatorValue("customer")
public class Customer extends User {

    public Customer() {
        super.userType = "customer";
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade= CascadeType.ALL)
    private Set<CustomerPlan> customerPlanList;

    public Set<CustomerPlan> getCustomerPlanList() {
        return customerPlanList;
    }

    public Customer setCustomerPlanList(Set<CustomerPlan> customerPlanList) {
        this.customerPlanList = customerPlanList;
        return this;
    }
}
