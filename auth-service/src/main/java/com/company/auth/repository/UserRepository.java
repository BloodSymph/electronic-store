package com.company.auth.repository;

import com.company.auth.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT user FROM User user " +
            "WHERE LOWER(user.username) " +
            "LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(user.email) " +
            "LIKE CONCAT('%', :searchText ,'%') " +
            "OR LOWER(user.profile.firstName) " +
            "LIKE CONCAT('%', :searchText ,'%') " +
            "OR LOWER(user.profile.lastName) " +
            "LIKE CONCAT('%', :searchText ,'%') " +
            "OR LOWER(user.profile.country) " +
            "LIKE CONCAT('%', :searchText ,'%') " +
            "OR LOWER(user.profile.city) " +
            "LIKE CONCAT('%', :searchText ,'%') " +
            "OR LOWER(user.profile.address) " +
            "LIKE CONCAT('%', :searchText ,'%') " +
            "OR LOWER(user.profile.phoneNumber) " +
            "LIKE CONCAT('%', :searchText ,'%') " +
            "OR LOWER(user.profile.mailAddress) " +
            "LIKE CONCAT('%', :searchText ,'%') "
    )
    Page<UserEntity> searchUsers(
            Pageable pageable,
            @Param("searchText") String searchText
    );

    @EntityGraph(value = "user-details-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT user FROM User user WHERE user.username LIKE LOWER(:username) ")
    Optional<UserEntity> getDetailsAboutUser(
           @Param("username") String username
    );

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    Boolean existsByUsernameIgnoreCase(String username);

    Boolean existsByVersion(Long userVersion);

    void deleteByUsername(String username);

}
