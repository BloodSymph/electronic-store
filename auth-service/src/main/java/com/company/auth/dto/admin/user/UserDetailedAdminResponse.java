package com.company.auth.dto.admin.user;

import com.company.auth.dto.admin.profile.ProfileAdminResponse;
import com.company.auth.dto.admin.role.RoleResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDetailedAdminResponse extends UserAdminResponse {

    private Set<RoleResponse> roles;

    private ProfileAdminResponse profileAdminResponse;

}
