package com.company.gateway.dto.auth;

import com.company.gateway.validator.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {

    @NotEmpty
    @NotBlank(message = "Username field shod not be empty!")
    @Length(max = 60, message = "Username field shod have maximum of {max} characters!")
    private String username;

    @NotEmpty
    @NotBlank(message = "Email field shod not be empty!")
    @Length(max = 60, message = "Email field shod have maximum of {max} characters!")
    @Email(message = "This field shod contains @ - character!")
    private String email;

    @NotEmpty
    @NotBlank(message = "Password field shod not be empty!")
    @Length(max = 25, message = "Password field shod have maximum of {max} characters!")
    @ValidPassword
    private String password;

}
