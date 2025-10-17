package com.example.dailydo.controller;

import com.example.dailydo.dto.UserDTO;
import com.example.dailydo.request.LoginRequest;
import com.example.dailydo.request.RegisterRequest;
import com.example.dailydo.response.ApiResponse;
import com.example.dailydo.service.interfaces.AuthService;
import com.example.dailydo.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthService authService;
    private final MessageUtil messageUtil;
    private final MessageSource messageSource;

    @PostMapping("/register")
    public ApiResponse<UserDTO> register(@Valid @RequestBody RegisterRequest request) {
        UserDTO user = authService.register(request);
        String msg = messageSource.getMessage("register.success", null, LocaleContextHolder.getLocale());
        return ApiResponse.success(user, msg);
    }

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@Valid @RequestBody LoginRequest request) {
        Map<String, Object> data = authService.login(request);
        return ApiResponse.success(data, messageUtil.get("login.success"));
    }

    @PostMapping("/refresh")
    public ApiResponse<Map<String, String>> refresh(@RequestBody Map<String,String> request) {
        String newAccessToken = authService.refreshToken(request.get("refreshToken"));
        return ApiResponse.success(Map.of("accessToken", newAccessToken), messageUtil.get("token.refreshed"));
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestParam String email) {
        authService.logout(email);
        return ApiResponse.success(null, messageUtil.get("logout.success"));
    }
}
