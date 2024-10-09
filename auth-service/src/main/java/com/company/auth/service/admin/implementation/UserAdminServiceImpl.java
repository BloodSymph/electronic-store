package com.company.auth.service.admin.implementation;

import com.company.auth.dto.admin.role.PermissionDto;
import com.company.auth.dto.admin.role.RoleRequest;
import com.company.auth.dto.admin.role.RoleResponse;
import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.dto.admin.user.UserDetailedAdminResponse;
import com.company.auth.entity.RoleEntity;
import com.company.auth.entity.UserEntity;
import com.company.auth.exception.exceptions.profile.ProfileVersionNotValidException;
import com.company.auth.exception.exceptions.role.RoleNotFoundException;
import com.company.auth.exception.exceptions.role.RoleVersionNotValidException;
import com.company.auth.exception.exceptions.user.UserNotFoundException;
import com.company.auth.exception.exceptions.user.UserVersionNotValidException;
import com.company.auth.mapper.admin.RoleMapper;
import com.company.auth.mapper.admin.UserAdminMapper;
import com.company.auth.repository.ProfileRepository;
import com.company.auth.repository.RoleRepository;
import com.company.auth.repository.TokenRepository;
import com.company.auth.repository.UserRepository;
import com.company.auth.service.admin.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.auth.mapper.admin.RoleMapper.mapToRoleRequestToEntity;
import static com.company.auth.mapper.admin.RoleMapper.mapToRoleResponse;
import static com.company.auth.mapper.admin.UserAdminMapper.mapToUserAdminDetailedResponse;
import static com.company.auth.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ProfileRepository profileRepository;

    @Override
    @Cacheable(value = "user_admin", unless = "#result == null ")
    public Page<UserAdminResponse> getListOfUsers(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(UserAdminMapper::mapToUserAdminResponse);
    }

    @Override
    @Cacheable(value = "user_admin", unless = "#result == null ")
    public Page<UserAdminResponse> searchUsers(Pageable pageable, String searchText) {
        return userRepository
                .searchUsers(pageable, searchText)
                .map(UserAdminMapper::mapToUserAdminResponse);
    }

    @Override
    public UserDetailedAdminResponse getUserDetails(String username) {
        UserEntity user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user by username: " + username + "!"
                        )
                );
        return mapToUserAdminDetailedResponse(user);
    }

    @Override
    @Transactional
    public void deleteUser(String username, Long userVersion) {
        if (!userRepository.existsByUsernameIgnoreCase(username)) {
            throw new UserNotFoundException(
                    "Can not find user by username: " + username + "!"
            );
        }
        if (!userRepository.existsByVersion(userVersion)) {
            throw new UserVersionNotValidException(
                    "User Entity version not valid!"
            );
        }
        userRepository.deleteByUsername(username);
    }

    @Override
    @Transactional
    public void deleteUserProfile(String username, Long profileVersion) {
        if (!profileRepository.existsByUser_Username(username)) {
            throw new RoleNotFoundException(
                    "Can not find profile by username: " + username + "!"
            );
        }
        if (!profileRepository.existsByVersion(profileVersion)) {
            throw new ProfileVersionNotValidException(
                    "Profile Entity version not valid!"
            );
        }
        profileRepository.deleteByUser_Username(username);
    }

    @Override
    @Cacheable(value = "role_admin", unless = "#result == null ")
    public Page<RoleResponse> getListOfRoles(Pageable pageable) {
        return roleRepository
                .findAll(pageable)
                .map(RoleMapper::mapToRoleResponse);
    }

    @Override
    @Cacheable(value = "role_admin", unless = "#result == null ")
    public Page<RoleResponse> searchRole(Pageable pageable, String roleName) {
        return roleRepository
                .searchRole(pageable, roleName)
                .map(RoleMapper::mapToRoleResponse);
    }

    @Override
    @CachePut(value = "role_admin", unless = "#result == null ")
    public RoleResponse createNewRole(RoleRequest roleRequest) {
        RoleEntity role = mapToRoleRequestToEntity(roleRequest);
        roleRepository.save(role);
        return mapToRoleResponse(role);
    }

    @Override
    @Transactional
    @CachePut(value = "role_admin", unless = "#result == null ")
    public RoleResponse updateCurrentRole(RoleRequest roleRequest, String roleName) {
        RoleEntity role = roleRepository
                .findByNameIgnoreCase(roleName)
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find role by name: " + roleName + "!"
                        )
                );
        role.setName(roleRequest.getName());
        role.setVersion(roleRequest.getVersion());
        roleRepository.save(role);
        return mapToRoleResponse(role);
    }

    @Override
    @Transactional
    public void givePermissionForUser(PermissionDto givePermissionDto) {

        UserEntity user = userRepository
                .findByUsernameIgnoreCase(
                        givePermissionDto.getUsername()
                ).orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by username: " + givePermissionDto.getUsername() + "!"
                        )
                );

        RoleEntity role = roleRepository
                .findByNameIgnoreCase(
                        givePermissionDto.getRoleName()
                ).orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find role by name: " + givePermissionDto.getRoleName() + "!"
                        )
                );

        user.getRoles().add(role);

        userRepository.save(user);

    }

    @Override
    @Transactional
    public void removePermission(PermissionDto givePermissionDto) {

        UserEntity user = userRepository
                .findByUsernameIgnoreCase(
                        givePermissionDto.getUsername()
                ).orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by username: " + givePermissionDto.getUsername() + "!"
                        )
                );

        RoleEntity role = roleRepository
                .findByNameIgnoreCase(
                        givePermissionDto.getRoleName()
                ).orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find role by name: " + givePermissionDto.getRoleName() + "!"
                        )
                );

        user.getRoles().remove(role);

        userRepository.save(user);

    }

    @Override
    @Transactional
    public void removeAllUsersPermissions(String username) {

        UserEntity user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by username: " + username + "!"
                        )
                );

        user.getRoles().clear();

        userRepository.save(user);

    }

    @Override
    @Transactional
    public void deleteRole(String roleName, Long roleVersion) {
        if (!roleRepository.existsByNameIgnoreCase(roleName)) {
            throw new RoleNotFoundException(
                    "Can not find role by name: " + roleName + "!"
            );
        }
        if (!roleRepository.existsByVersion(roleVersion)) {
            throw new RoleVersionNotValidException(
                    "Role Entity version not valid!"
            );
        }
        roleRepository.deleteByNameIgnoreCase(roleName);
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }

}
