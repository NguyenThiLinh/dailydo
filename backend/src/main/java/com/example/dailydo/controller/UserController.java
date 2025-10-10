package com.example.dailydo.controller;

import com.example.dailydo.dto.UserDTO;
import com.example.dailydo.response.ApiResponse;
import com.example.dailydo.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody @Valid UserDTO.Request request) {
        UserDTO userCreated = userService.createUser(request);
        return ResponseEntity.ok(ApiResponse.success(userCreated,"User created successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllActiveUsers() {
        List<UserDTO> users = userService.getAllActiveUsers();
        return ResponseEntity.ok(ApiResponse.success(users,"Users fetched successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success(user,"User fetched successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable long id, @RequestBody @Valid UserDTO.Request request) {
        UserDTO userUpdated = userService.updateUser(id, request);
        return ResponseEntity.ok(ApiResponse.success(userUpdated,"User updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> deleteUser(@PathVariable long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok(ApiResponse.success(null,"User deleted successfully"));
    }
}
