package com.company.cart.repository;

import com.company.cart.entity.CartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    @EntityGraph(value = "cart-items-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    Optional<CartEntity> findByProfileId(Long profileId);

    @Query("SELECT cart FROM Cart cart WHERE cart.profileId = :profileId ")
    Page<CartEntity> searchByProfileId(
            Pageable pageable, @Param(value = "profileId") Long profileId
    );

    void deleteByProfileId(Long profileId);
    Boolean existsByVersion(Long cartVersion);
    Boolean existsByProfileId(Long profileId);
}
