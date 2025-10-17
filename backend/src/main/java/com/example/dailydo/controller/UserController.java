package com.example.dailydo.controller;

import com.example.dailydo.dto.UserDTO;
import com.example.dailydo.request.CreateUserRequest;
import com.example.dailydo.request.UpdateUserRequest;
import com.example.dailydo.response.ApiResponse;
import com.example.dailydo.service.interfaces.UserService;
import com.example.dailydo.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageUtil messageUtil;

    // === GET ALL USERS ===
    @GetMapping
    public ApiResponse<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ApiResponse.success(users, messageUtil.get("user.list.success", "Users retrieved successfully"));
    }

    // === GET USER BY ID ===
    @GetMapping("/{id}")
    public ApiResponse<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ApiResponse.success(user, messageUtil.get("user.get.success", "User retrieved successfully"));
    }

    // === CREATE NEW USER (ADMIN) ===
    @PostMapping
    public ApiResponse<UserDTO> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserDTO user = userService.createUser(request);
        return ApiResponse.success(user, messageUtil.get("register.success"));
    }

    // === UPDATE USER ===
    @PutMapping("/{id}")
    public ApiResponse<UserDTO> updateUser(@PathVariable Long id,
                                           @Valid @RequestBody UpdateUserRequest request) {
        UserDTO updatedUser = userService.updateUser(id, request);
        return ApiResponse.success(updatedUser, messageUtil.get("user.update.success", "User updated successfully"));
    }

    // === DELETE USER (SOFT DELETE) ===
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.softDeleteUser(id);
        String message = deleted
                ? messageUtil.get("user.delete.success", "User deleted successfully")
                : messageUtil.get("user.delete.failed", "User not found");
        return ApiResponse.success(null, message);
    }
}
