package com.example.dailydo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String error;
    private LocalDateTime timestamp;

    // === Success ===
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // === Error (simple) ===
    public static ApiResponse<?> error(String message, String errorType) {
        return ApiResponse.builder()
                .success(false)
                .message(message)
                .error(errorType)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // === Error (with detailed data, e.g. validation errors) ===
    public static <T> ApiResponse<T> error(String message, T errorData) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(errorData)
                .timestamp(LocalDateTime.now())
                .build();
    }
}