package com.springlove.api_demo.controller;

import com.springlove.api_demo.dto.OrderCreateDto;
import com.springlove.api_demo.entity.Order;
import com.springlove.api_demo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST /api/orders
    @PostMapping("/orders")
    public Order addOrder(@Valid @RequestBody OrderCreateDto orderCreateDto) {
        return orderService.addOrder(orderCreateDto);
    }

    // GET /api/orders
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // GET /api/orders/{id}
    @GetMapping("/orders/{id}")
    public Order findOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }


}
