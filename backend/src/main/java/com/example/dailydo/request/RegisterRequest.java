package com.example.dailydo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "{firstName.required}")
    private String firstName;

    @NotBlank(message = "{lastName.required}")
    private String lastName;

    @Email(message = "{email.invalid}")
    @NotBlank(message = "{email.required}")
    private String email;

    @NotBlank(message = "{password.required}")
    @Size(min = 6, message = "{password.min}")


    private String password;

    @NotBlank(message="{field.required}")
    private String confirmPassword;

    private Long roleId;
}