package com.company.auth.mapper.admin;

import com.company.auth.dto.admin.role.RoleRequest;
import com.company.auth.dto.admin.role.RoleResponse;
import com.company.auth.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public static RoleResponse mapToRoleResponse(RoleEntity role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .created(role.getCreated())
                .updated(role.getUpdated())
                .version(role.getVersion())
                .build();
    }

    public static RoleEntity mapToRoleRequestToEntity(RoleRequest role) {
        return RoleEntity.builder()
                .name(role.getName())
                .version(role.getVersion())
                .build();
    }

}
