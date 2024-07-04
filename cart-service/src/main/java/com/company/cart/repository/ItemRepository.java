package com.company.cart.repository;

import com.company.cart.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query("SELECT sum(items.price) FROM Item items WHERE items.cart.profileId = :profileId")
    Double countAllByPrice(@Param(value = "profileId") Long profileId);

    void deleteByProductIdAndCart_ProfileId(Long itemId, Long profileId);

    void deleteAllByCart_ProfileId(Long profileId);

    Boolean existsByVersion(Long itemVersion);

    Boolean existsByProductId(Long itemId);

}
