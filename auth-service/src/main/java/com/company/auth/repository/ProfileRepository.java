package com.company.auth.repository;

import com.company.auth.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    @Query("SELECT profile FROM Profile profile WHERE profile.user.username LIKE LOWER(:username) ")
    Optional<ProfileEntity> findByUser_Username(
            @Param(value = "username") String username
    );

    @Query("SELECT profile FROM Profile profile WHERE profile.user.username LIKE LOWER(:username) ")
    Boolean existsByUser_Username(
            @Param(value = "username") String username
    );

    Boolean existsByVersion(Long profileVersion);

    @Modifying
    @Query("DELETE FROM Profile profile WHERE profile.user.username LIKE LOWER(:username) ")
    void deleteByUser_Username(
            @Param(value = "username") String username
    );

}
