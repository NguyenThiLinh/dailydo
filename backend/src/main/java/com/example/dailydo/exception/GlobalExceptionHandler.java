package com.example.dailydo.exception;

import com.example.dailydo.exception.custom.EmailAlreadyExistsException;
import com.example.dailydo.response.ApiResponse;
import com.example.dailydo.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    // ==== Validation errors ====
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            // Lấy key từ annotation (nếu có {} thì remove)
            String messageKey = error.getDefaultMessage();
            if (messageKey != null) {
                messageKey = messageKey.replace("{", "").replace("}", "");

                // Lấy nhãn field từ messages.properties (vd: user.confirmPassword)
                String fieldLabel = messageSource.getMessage(
                        "user." + error.getField(),
                        null,
                        error.getField(),
                        LocaleContextHolder.getLocale()
                );

                // Resolve message, truyền fieldLabel vào {0} nếu template có
                String resolvedMessage = messageSource.getMessage(
                        messageKey,
                        new Object[]{fieldLabel},
                        messageKey,
                        LocaleContextHolder.getLocale()
                );

                errors.put(error.getField(), resolvedMessage);
            } else {
                errors.put(error.getField(), error.getDefaultMessage());
            }
        });

        String message = messageSource.getMessage(
                "validation.failed",
                null,
                "Validation failed",
                LocaleContextHolder.getLocale()
        );

        return ResponseEntity.badRequest()
                .body(ApiResponse.error(message, ErrorCode.VALIDATION_ERROR, errors));
    }

    // ==== Email duplicate ====
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleEmailExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage(), ErrorCode.EMAIL_DUPLICATE));
    }

    // ==== Bad request body ====
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequest(HttpMessageNotReadableException ex) {
        String message = messageSource.getMessage("error.badRequest", null, "Request body is missing or invalid", LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(message, ErrorCode.BAD_REQUEST));
    }

    // ==== Runtime exceptions ====
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage(), ErrorCode.BAD_REQUEST));
    }

    // ==== Fallback ====
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneric(Exception ex) {
        String message = messageSource.getMessage("error.internal", null, "Internal server error", LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(message, ErrorCode.INTERNAL_ERROR));
    }
}
