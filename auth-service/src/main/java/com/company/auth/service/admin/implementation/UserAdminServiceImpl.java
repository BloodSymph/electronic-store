package com.company.auth.service.admin.implementation;

import com.company.auth.dto.admin.role.PermissionDto;
import com.company.auth.dto.admin.role.RoleRequest;
import com.company.auth.dto.admin.role.RoleResponse;
import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.dto.admin.user.UserDetailedAdminResponse;
import com.company.auth.repository.ProfileRepository;
import com.company.auth.repository.RoleRepository;
import com.company.auth.repository.TokenRepository;
import com.company.auth.repository.UserRepository;
import com.company.auth.service.admin.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.auth.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
@CacheConfig
public class UserAdminServiceImpl implements UserAdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ProfileRepository profileRepository;

    private final TokenRepository tokenRepository;

    @Override
    public Page<UserAdminResponse> getListOfUsers(Pageable pageable) {
        return null;
    }

    @Override
    public Page<UserAdminResponse> searchUsers(Pageable pageable, String searchText) {
        return null;
    }

    @Override
    public UserDetailedAdminResponse getUserDetails(String username) {
        return null;
    }

    @Override
    @Transactional
    public void deleteUser(String username, Long userVersion) {

    }

    @Override
    @Transactional
    public void deleteUsersProfile(String username, Long profileVersion) {

    }

    @Override
    public Page<RoleResponse> getListOfRoles(Pageable pageable) {
        return null;
    }

    @Override
    public RoleResponse createNewRole(RoleRequest roleRequest) {
        return null;
    }

    @Override
    @Transactional
    public RoleResponse updateCurrentRole(RoleRequest roleRequest, String roleName) {
        return null;
    }

    @Override
    @Transactional
    public void givePermissionForUser(PermissionDto givePermissionDto) {

    }

    @Override
    @Transactional
    public void removePermission(PermissionDto givePermissionDto) {

    }

    @Override
    @Transactional
    public void removeAllUsersPermissions(String username) {

    }

    @Override
    @Transactional
    public void deleteRole(String roleName, Long roleVersion) {

    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }
}
