package com.company.auth.service.admin;

import com.company.auth.dto.admin.role.PermissionDto;
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

    Page<UserAdminResponse> searchUsers(Pageable pageable, String searchText);

    UserDetailedAdminResponse getUserDetails(String username);

    void deleteUser(String username, Long userVersion);

    void deleteUsersProfile(String username, Long profileVersion);

    Page<RoleResponse> getListOfRoles(Pageable pageable);

    RoleResponse createNewRole(RoleRequest roleRequest);

    RoleResponse updateCurrentRole(RoleRequest roleRequest, String roleName);

    void givePermissionForUser(PermissionDto givePermissionDto);

    void removePermission(PermissionDto givePermissionDto);

    void removeAllUsersPermissions(String username);

    void deleteRole(String roleName, Long roleVersion);

    //todo:Clear all users tokens

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}
