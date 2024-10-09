package com.company.auth.controller.admin;

import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.dto.admin.user.UserDetailedAdminResponse;
import com.company.auth.service.admin.UserAdminService;
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
      @RequestParam(value = "searchText", required = false, defaultValue = "") String searchText) {
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



}
