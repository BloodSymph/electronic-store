package com.company.auth.security.service;

import com.company.auth.entity.RoleEntity;
import com.company.auth.entity.UserEntity;
import com.company.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private Collection<GrantedAuthority> mapRolesAuthority(Set<RoleEntity> roles) {
        return roles.stream()
                .map(
                        role -> new SimpleGrantedAuthority(
                                role.getName()
                        )
                ).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(
            String username) throws UsernameNotFoundException {

        UserEntity user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user by current username: " + username + " !"
                        )
                );

        return new User(
               user.getUsername(),
               user.getPassword(),
               mapRolesAuthority(
                       user.getRoles()
               )
        );

    }

}
