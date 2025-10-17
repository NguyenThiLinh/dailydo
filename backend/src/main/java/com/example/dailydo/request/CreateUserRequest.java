package com.example.dailydo.request;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    @NotBlank(message = "{field.required}")
    private String firstName;

    @NotBlank(message = "{field.required}")
    private String lastName;

    @NotBlank(message = "{field.required}")
    @Email(message = "{field.invalid}")
    private String email;

    @NotBlank(message = "{field.required}")
    @Size(min = 6, message = "{field.minlength}")
    private String password;

    @NotNull(message = "{field.required}")
    private Long roleId;
}