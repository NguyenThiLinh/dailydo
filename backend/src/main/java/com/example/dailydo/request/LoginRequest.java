package com.example.dailydo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "{field.required}")
    private String email;

    @NotBlank(message = "{field.required}")
    private String password;

}

