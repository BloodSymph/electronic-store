package com.company.cart.repository;

import com.company.cart.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query("SELECT count(items.price) FROM Item items WHERE items.cart.profileId = :profileId")
    Double countAllByPrice(@Param(value = "profileId") Long profileId);

    void deleteByUrlIgnoreCaseAndCart_ProfileId(String itemUrl, Long profileId);

    void deleteAllByCart_ProfileId(Long profileId);

    Boolean existsByVersion(Long itemVersion);

    Boolean existsByUrlIgnoreCase(String itemUrl);

}
