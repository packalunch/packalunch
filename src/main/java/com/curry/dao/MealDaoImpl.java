package com.curry.dao;


import com.curry.fw.dao.AbstractDao;
import com.curry.model.Meal;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 11/1/14.
 */
@Repository(value="mealDao")
public class MealDaoImpl extends AbstractDao <Meal>
        implements MealDao {

    static Logger log = Logger.getLogger(MealDaoImpl.class);

    public MealDaoImpl() {
        super(Meal.class);
    }

    @Override
    public void save(Meal meal) {
        super.save(meal);
    }

    @Override
    public void save(List<Meal> meals) {
        super.saveAll(meals);
    }

    @Override
    public List<Meal> listByCustomerByRange(int id, Date startDate, Date endDate) {
        Query query = getSession().createQuery("FROM Meal WHERE customer_id = :customerId " +
                "AND date between :startDate and :endDate");
        query.setParameter("customerId", id);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        log.info(startDate);
        log.info(endDate);

        List<Meal> customerMealList = query.list();

        return customerMealList;
    }

    public void deleteMeal(Meal meal) {
        super.delete(meal);
    }

}
