package com.springlove.api_demo.service;

import com.springlove.api_demo.dto.OrderCreateDto;
import com.springlove.api_demo.entity.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(OrderCreateDto orderCreateDto);

    List<Order> getAllOrders();

    Order getOrderById(int id);
}
