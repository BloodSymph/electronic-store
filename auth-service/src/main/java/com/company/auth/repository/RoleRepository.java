package com.company.auth.repository;

import com.company.auth.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByNameIgnoreCase(String roleName);

    Boolean existsByVersion(Long roleVersion);

    Boolean existsByName(String roleName);

    void deleteByNameIgnoreCase(String roleName);

}