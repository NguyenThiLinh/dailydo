package com.example.dailydo.controller;

import com.example.dailydo.response.ApiResponse;
import com.example.dailydo.security.JwtTokenProvider;
import com.example.dailydo.dto.UserDTO;

import com.example.dailydo.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody UserDTO.Request request) {
        request.setRoleId(1L);
        UserDTO createUser = userService.createUser(request);
        return ResponseEntity.ok(ApiResponse.success(createUser, "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody UserDTO.Login loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        return ResponseEntity.ok(
                ApiResponse.success(
                        Map.of("accessToken", accessToken, "refreshToken", refreshToken),
                        "Login successfully"
                )
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<?>> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (refreshToken == null || !jwtTokenProvider.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("Invalid refresh token", "TOKEN_INVALID"));
        }

        String username = jwtTokenProvider.getUsernameFromToken(refreshToken);
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, null);
        String newAccessToken = jwtTokenProvider.generateAccessToken(authentication);

        return ResponseEntity.ok(ApiResponse.success(
                Map.of("accessToken", newAccessToken),
                "Token refreshed"
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(@RequestBody(required = false) Map<String, String> request) {
        return ResponseEntity.ok(ApiResponse.success(null, "Logout successfully"));
    }
}
