package com.company.auth.service.admin;

import com.company.auth.dto.admin.role.PermissionRequest;
import com.company.auth.dto.admin.role.RoleRequest;
import com.company.auth.dto.admin.role.RoleResponse;
import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.dto.admin.user.UserDetailedAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface UserAdminService {

    Page<UserAdminResponse> getListOfUsers(Pageable pageable);

    Page<UserAdminResponse> searchUsers(
            Pageable pageable, String searchText
    );

    UserDetailedAdminResponse getUserDetails(String username);

    void deleteUser(
            String username, Long userVersion
    );

    void deleteUserProfile(
            String username, Long profileVersion
    );

    Page<RoleResponse> getListOfRoles(Pageable pageable);

    Page<RoleResponse> searchRole(
            Pageable pageable, String roleName
    );

    RoleResponse createNewRole(RoleRequest roleRequest);

    RoleResponse updateCurrentRole(
            RoleRequest roleRequest, String roleName
    );

    void givePermissionForUser(PermissionRequest givePermissionDto);

    void removePermission(PermissionRequest givePermissionDto);

    void removeAllUsersPermissions(String username);

    void deleteRole(
            String roleName, Long roleVersion
    );

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}
