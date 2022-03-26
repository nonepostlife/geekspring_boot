package com.geekbrains.geekspring.repositories;

import com.geekbrains.geekspring.entities.OrderItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem, Long> {

}
