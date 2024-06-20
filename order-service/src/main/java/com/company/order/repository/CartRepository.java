package com.company.order.repository;

import com.company.order.dto.ItemResponse;
import com.company.order.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Long, CartEntity> {


}
