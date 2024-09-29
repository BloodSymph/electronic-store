package com.company.auth.mapper.admin;

import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.dto.admin.user.UserDetailedAdminResponse;
import com.company.auth.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.company.auth.mapper.admin.ProfileAdminMapper.mapToProfileAdminResponse;

@Component
public class UserAdminMapper {

    public static UserAdminResponse mapToUserAdminResponse(UserEntity user) {
        return UserAdminResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .created(user.getCreated())
                .updated(user.getUpdated())
                .version(user.getVersion())
                .build();
    }

    public static UserDetailedAdminResponse mapToUserAdminDetailedResponse(UserEntity user) {
        return UserDetailedAdminResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(RoleMapper::mapToRoleResponse)
                                .collect(
                                        Collectors.toSet()
                                )
                )
                .profileAdminResponse(
                        mapToProfileAdminResponse(
                                user.getProfile()
                        )
                )
                .created(user.getCreated())
                .updated(user.getUpdated())
                .version(user.getVersion())
                .build();
    }

}
