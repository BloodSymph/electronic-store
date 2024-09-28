package com.company.auth.dto.client;

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
public class ProfileClientRequest {

    @NotEmpty
    @NotBlank(message = "First name field shod not be empty!")
    @Length(max = 120, message = "First name field shod have maximum of {max} characters!")
    private String firstName;

    @NotEmpty
    @NotBlank(message = "Last name field shod not be empty!")
    @Length(max = 120, message = "Last name field shod have maximum of {max} characters!")
    private String lastName;

    @NotEmpty
    @NotBlank(message = "Phone number field shod not be empty!")
    @Length(max = 120, message = "Phone number field shod have maximum of {max} characters!")
    private String phoneNumber;

    @NotEmpty
    @NotBlank(message = "Country field shod not be empty!")
    @Length(max = 120, message = "Country field shod have maximum of {max} characters!")
    private String country;

    @NotEmpty
    @NotBlank(message = "City field shod not be empty!")
    @Length(max = 120, message = "City field shod have maximum of {max} characters!")
    private String city;

    @NotEmpty
    @NotBlank(message = "Address field shod not be empty!")
    @Length(max = 120, message = "Address field shod have maximum of {max} characters!")
    private String address;

    @NotEmpty
    @NotBlank(message = "Mail address field shod not be empty!")
    @Length(max = 120, message = "Mail address field shod have maximum of {max} characters!")
    private String mailAddress;

    @NotNull(message = "Version field shod not contains null value!")
    private Long version;

}
