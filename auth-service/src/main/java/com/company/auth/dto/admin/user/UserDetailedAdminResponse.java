package com.company.auth.dto.admin.user;

import com.company.auth.dto.admin.profile.ProfileAdminResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDetailedAdminResponse extends UserAdminResponse {

    //todo: Role response

    private ProfileAdminResponse profileAdminResponse;

}
