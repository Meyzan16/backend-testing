package com.example.auth_service.shared.Exception;


import com.example.auth_service.auth.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ApiResponse<Object>> handleAuthException(AuthException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(
                        ex.getMessage(),
                        ex.getErrors()
                ));
    }

    @ExceptionHandler(NotifException.class)
    public ResponseEntity<ApiResponse<Object>> handleAuthException(NotifException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(
                        ex.getMessage(),
                        ex.getErrors()
                ));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        AuthException validationException =
                new AuthException.ValidationException(errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        validationException.getMessage(),
                        validationException.getErrors()
                ));
    }
    

}