package com.geekbrains.geekspring.services;

import com.geekbrains.geekspring.entities.*;
import com.geekbrains.geekspring.repositories.OrderItemRepository;
import com.geekbrains.geekspring.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public boolean save(User user, List<CartItem> cart, double total) {
        Order order = new Order();
        order.setId(0L);
        order.setNum("O-" + user.getId() + '-' + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
        order.setUser_id(user);
        order.setTotal(total);
        orderRepository.save(order);

        for (CartItem i : cart) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder_id(order);
            orderItem.setProduct_id(i.getProduct());
            orderItem.setQuantity(i.getQuantity());
            orderItemRepository.save(orderItem);
        }
        return true;
    }
}
