package com.company.auth.repository;

import com.company.auth.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    @Query("SELECT token FROM Token token " +
            "INNER JOIN User owner ON token.owner.id = owner.id " +
            "WHERE token.owner.id = :ownerId AND token.loggedOut = false"
    )
    List<TokenEntity> findAllAccessTokenByOwnerId(
            @Param("ownerId") Long ownerId
    );

    Optional<TokenEntity> findByAccessToken(String accessToken);

    Optional<TokenEntity> findByRefreshToken(String refreshToken);

}
