package com.company.order.repository;

import com.company.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT order FROM Order order WHERE order.profileId = :profileId")
    Optional<OrderEntity> showOrderByProfileId(
            @Param(value = "profileId") Long profileId
    );

    void deleteByProfileId(Long profileId);

    Boolean existsByProfileId(Long profileId);

    Boolean existsByVersion(Long orderVersion);

}
