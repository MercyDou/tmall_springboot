package com.tmall.dao;

import com.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
    public User findByName(String name);

    public User getByNameAndPassword(String name, String password);
}
