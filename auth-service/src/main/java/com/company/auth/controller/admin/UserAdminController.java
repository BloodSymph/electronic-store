package com.company.auth.controller.admin;

import com.company.auth.dto.admin.role.PermissionRequest;
import com.company.auth.dto.admin.role.RoleRequest;
import com.company.auth.dto.admin.role.RoleResponse;
import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.dto.admin.user.UserDetailedAdminResponse;
import com.company.auth.service.admin.UserAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/auth-service/admin")
public class UserAdminController {

    public final UserAdminService userAdminService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserAdminResponse> getUsersList(
            @PageableDefault(
                sort = "username",
                direction = Sort.Direction.ASC,
                page = 0,
                size = 20
    ) Pageable pageable) {
        return userAdminService.getListOfUsers(pageable);
    }

    @GetMapping("/users/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserAdminResponse> searchUsers(
            @PageableDefault(
                sort = "username",
                direction = Sort.Direction.ASC,
                page = 0,
                size = 20
    ) Pageable pageable,
            @RequestParam(
                    value = "searchText",
                    required = false,
                    defaultValue = ""
            ) String searchText) {
        return userAdminService.searchUsers(pageable, searchText);
    }

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailedAdminResponse getUserDetails(
            @PathVariable(value = "username") String username) {
        return userAdminService.getUserDetails(username);
    }

    @DeleteMapping("/users/{username}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteUser(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "userVersion") Long userVersion) {
        userAdminService.deleteUser(username, userVersion);
        return new ResponseEntity<>(
                "User is successful deleted!",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/users/{username}/profile/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteUsersProfile(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "profileVersion") Long profileVersion) {
        userAdminService.deleteUserProfile(username, profileVersion);
        return new ResponseEntity<>(
                "User profile is successful deleted!",
                HttpStatus.OK
        );
    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public Page<RoleResponse> getListOfRoles(
            @PageableDefault(
                sort = "name",
                direction = Sort.Direction.ASC,
                page = 0,
                size = 20
    ) Pageable pageable) {
        return userAdminService.getListOfRoles(pageable);
    }

    @GetMapping("/roles/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<RoleResponse> searchRoles(
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 20
    ) Pageable pageable,
            @RequestParam(
                    value = "name",
                    required = false,
                    defaultValue = ""
            ) String name) {
        return userAdminService.searchRole(pageable, name);
    }

    @PostMapping("/roles/create")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse createRole(
            @Valid @RequestBody RoleRequest roleRequest) {
        return userAdminService.createNewRole(roleRequest);
    }

    @PutMapping("/roles/{roleName}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse updateRole(
            @Valid @RequestBody RoleRequest roleRequest,
            @PathVariable(value = "roleName") String roleName) {
        return userAdminService.updateCurrentRole(roleRequest, roleName);
    }

    @PostMapping("/roles/give-permission")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> givePermission(
            @Valid @RequestBody PermissionRequest permissionRequest) {
        userAdminService.givePermissionForUser(permissionRequest);
        return new ResponseEntity<>(
                "Permission is successful given!",
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping("/roles/remove-permission")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removePermission(
            @Valid @RequestBody PermissionRequest permissionRequest) {
        userAdminService.removePermission(permissionRequest);
        return new ResponseEntity<>(
                "Permission is successful removed!",
                HttpStatus.OK
        );
    }

    @DeleteMapping("{username}/roles/remove-all-permissions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeAllPermission(
            @PathVariable(value = "username") String username) {
        userAdminService.removeAllUsersPermissions(username);
        return new ResponseEntity<>(
                "Permission is successful removed!",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/roles/{roleName}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteRole(
            @PathVariable(value = "roleName") String roleName,
            @RequestParam(value = "roleVersion") Long roleVersion) {
        userAdminService.deleteRole(roleName, roleVersion);
        return new ResponseEntity<>(
                "Role is successful deleted!",
                HttpStatus.OK
        );
    }

}
