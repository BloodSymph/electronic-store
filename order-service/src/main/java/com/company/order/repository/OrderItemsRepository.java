package com.company.order.repository;

import com.company.order.entity.OrderedItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderedItemsEntity, Long> {
}
