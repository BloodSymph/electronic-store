package com.company.auth.dto.admin.role;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {

    private String username;

    private String roleName;

}
