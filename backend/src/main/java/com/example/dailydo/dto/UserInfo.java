package com.example.dailydo.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfo {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long roleId;
}
