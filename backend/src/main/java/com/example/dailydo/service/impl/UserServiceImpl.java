package com.example.dailydo.service.impl;

import com.example.dailydo.dto.UserDTO;
import com.example.dailydo.exception.custom.EmailAlreadyExistsException;
import com.example.dailydo.exception.ResourceNotFoundException;
import com.example.dailydo.model.Role;
import com.example.dailydo.model.User;
import com.example.dailydo.repository.RoleRepository;
import com.example.dailydo.repository.UserRepository;
import com.example.dailydo.request.CreateUserRequest;
import com.example.dailydo.request.UpdateUserRequest;
import com.example.dailydo.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role", request.getRoleId()));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        return UserDTO.fromEntity(userRepository.save(user));
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserDTO::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }
    @Override public List<UserDTO> getAllActiveUsers() {
        List<User> users = userRepository.findByDeletedAtIsNull();
        return users.stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
                throw new EmailAlreadyExistsException(request.getEmail());
            });
            user.setEmail(request.getEmail());
        }

        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getPassword() != null) user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getRoleId() != null) {
            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Role", request.getRoleId()));
            user.setRole(role);
        }

        user.setUpdatedAt(LocalDateTime.now());
        return UserDTO.fromEntity(userRepository.save(user));
    }

    @Override
    public boolean softDeleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setDeletedAt(LocalDateTime.now());
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }

    // Nếu muốn lưu token khi login
    public void setTokens(Long userId, String accessToken, String refreshToken) {
        // lưu access/refresh token vào DB nếu muốn
    }
}