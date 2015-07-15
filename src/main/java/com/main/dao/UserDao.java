package com.main.dao;

import com.main.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * sadra
 * Created by sadra on 10/30/14.
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
