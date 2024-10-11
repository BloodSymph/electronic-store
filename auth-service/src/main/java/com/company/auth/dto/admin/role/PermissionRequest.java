package com.company.auth.dto.admin.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequest {

    @NotEmpty
    @NotBlank(message = "Username field shod not be empty!")
    @Length(max = 120, message = "Username field shod have maximum of {max} characters!")
    private String username;

    @NotEmpty
    @NotBlank(message = "Role name field shod not be empty!")
    @Length(max = 120, message = "Role name field shod have maximum of {max} characters!")
    private String roleName;

}
