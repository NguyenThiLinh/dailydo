package com.example.dailydo.request;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long roleId;
}
