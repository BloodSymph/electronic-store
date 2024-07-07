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

    @EntityGraph(value = "order-entity-graph-with-ordered-items", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT orders FROM Order orders WHERE orders.profileId = :profileId")
    Optional<OrderEntity> showOrderByProfileId(
            @Param(value = "profileId") Long profileId
    );

    void deleteByProfileId(Long profileId);

    Boolean existsByProfileId(Long profileId);

    Boolean existsByVersion(Long profileId);

}
