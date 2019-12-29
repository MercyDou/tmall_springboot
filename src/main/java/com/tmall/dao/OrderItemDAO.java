package com.tmall.dao;

import com.tmall.pojo.Order;
import com.tmall.pojo.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
}