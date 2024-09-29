package com.company.auth.dto.admin.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {

    @NotEmpty
    @NotBlank(message = "Role name field shod not be empty!")
    @Length(max = 120, message = "Role name field shod have maximum of {max} characters!")
    private String name;

    @NotNull(message = "Version field shod not contains null value!")
    private Long version;

}
