package com.example.dailydo.service.interfaces;

import com.example.dailydo.dto.UserDTO;
import com.example.dailydo.model.User;
import com.example.dailydo.request.CreateUserRequest;
import com.example.dailydo.request.UpdateUserRequest;

import java.util.List;

public interface UserService {

    UserDTO createUser(CreateUserRequest request);

    List<UserDTO> getAllUsers();

    List<UserDTO> getAllActiveUsers();

    UserDTO getUserById(Long id);

    UserDTO updateUser(Long id, UpdateUserRequest request);

    boolean softDeleteUser(Long id);

    void setTokens(Long userId, String accessToken, String refreshToken);
}