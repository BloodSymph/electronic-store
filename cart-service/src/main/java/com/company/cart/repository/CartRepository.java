package com.company.cart.repository;

import com.company.cart.entity.CartEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    //todo: Make getting items by username or profile id
    @EntityGraph(value = "cart-items-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    Optional<CartEntity> findByProfileId(Long profileId);

    Boolean existsByVersion(Long cartVersion);

}
