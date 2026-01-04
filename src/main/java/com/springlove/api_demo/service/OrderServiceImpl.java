package com.springlove.api_demo.service;

import com.springlove.api_demo.dto.OrderCreateDto;
import com.springlove.api_demo.entity.Order;
import com.springlove.api_demo.entity.Product;
import com.springlove.api_demo.enumdemo.OrderStatus;
import com.springlove.api_demo.exception.business.InsufficientStockException;
import com.springlove.api_demo.exception.common.ResourceNotFoundException;
import com.springlove.api_demo.repository.OrderRepository;
import com.springlove.api_demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // POST /api/orders
    @Override
    @Transactional
    public Order addOrder(OrderCreateDto orderCreateDto) {
        Product product = productRepository
                .findById(orderCreateDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found (ID = " + orderCreateDto.getProductId() + ")"
                ));

        if (product.getStock() < orderCreateDto.getQuantity()) {
            throw new InsufficientStockException(
                    "Not enough stock for product: " + product.getName()
            );
        }

        product.setStock(product.getStock() - orderCreateDto.getQuantity());
        productRepository.save(product);

        Order order = new Order();
        order.setProduct(product);
        order.setQuantity(orderCreateDto.getQuantity());

        return orderRepository.save(order);
    }

    // GET /api/orders
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // GET /api/orders/{id}
    @Override
    public Order getOrderById(int id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found (ID = " + id + ")"
                ));
    }

}
