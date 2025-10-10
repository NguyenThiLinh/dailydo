package com.example.dailydo.service.interfaces;

import com.example.dailydo.dto.UserDTO;
import com.example.dailydo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO createUser(UserDTO.Request request);

    List<UserDTO> getAllUsers();

    List<UserDTO> getAllActiveUsers();

    UserDTO getUserById(Long id);

    UserDTO updateUser(Long id, UserDTO.Request request);

    boolean softDeleteUser(Long id);

}
