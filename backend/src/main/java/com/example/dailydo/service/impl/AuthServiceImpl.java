package com.example.dailydo.service.impl;

import com.example.dailydo.dto.UserDTO;
import com.example.dailydo.exception.ResourceNotFoundException;
import com.example.dailydo.exception.custom.EmailAlreadyExistsException;
import com.example.dailydo.model.Role;
import com.example.dailydo.model.User;
import com.example.dailydo.repository.RoleRepository;
import com.example.dailydo.repository.UserRepository;
import com.example.dailydo.request.LoginRequest;
import com.example.dailydo.request.RegisterRequest;
import com.example.dailydo.response.ApiResponse;
import com.example.dailydo.security.CustomUserDetails;
import com.example.dailydo.security.JwtTokenProvider;
import com.example.dailydo.service.interfaces.AuthService;
import com.example.dailydo.service.interfaces.UserService;
import com.example.dailydo.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MessageUtil messageUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public UserDTO register(RegisterRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException(messageUtil.get("register.password.notmatch"));
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(messageUtil.get("email.duplicate"));
        }

        Role role = roleRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Role", 1L));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);
        return UserDTO.fromEntity(user);
    }

    @Override
    public Map<String, Object> login(LoginRequest request) {
        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            String accessToken = jwtTokenProvider.generateAccessToken(auth);
            String refreshToken = jwtTokenProvider.generateRefreshToken(auth);

            // Lưu token nếu cần
            userService.setTokens(userDetails.getId(), accessToken, refreshToken);

            // Map trực tiếp từ userDetails sang UserDTO
            UserDTO userDTO = UserDTO.builder()
                    .id(userDetails.getId())
                    .firstName(userDetails.getFullName().split(" ")[0])
                    .lastName(userDetails.getFullName().split(" ")[1])
                    .email(userDetails.getUsername())
                    .roleId(userDetails.getAuthorities().iterator().next().getAuthority().equals("ADMIN") ? 1L : 2L)
                    .build();

            return Map.of(
                    "user", userDTO,
                    "accessToken", accessToken,
                    "refreshToken", refreshToken
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException(messageUtil.get("login.failed"));
        }
    }

    @Override
    public void logout(String email) {
        // nếu track token → invalidate token
    }

    @Override
    public String refreshToken(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException(messageUtil.get("token.invalid"));
        }

        String username = jwtTokenProvider.getUsernameFromToken(refreshToken);
        var auth = new UsernamePasswordAuthenticationToken(username, null, null);
        return jwtTokenProvider.generateAccessToken(auth);
    }
}