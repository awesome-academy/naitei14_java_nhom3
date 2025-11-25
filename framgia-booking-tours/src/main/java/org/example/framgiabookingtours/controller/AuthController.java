package org.example.framgiabookingtours.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.framgiabookingtours.dto.ApiResponse;
import org.example.framgiabookingtours.dto.CustomUserDetails;
import org.example.framgiabookingtours.service.CustomUserDetailsService;
import org.example.framgiabookingtours.util.JwtUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/auth")
public class AuthController {
    JwtUtils jwtUtils;
    CustomUserDetailsService customUserDetailsService;

    @Operation(summary = "Test gửi accessToken cho user")
    @GetMapping
    public ApiResponse<Object> testToken(@RequestParam String email) {
        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        String accessToken = jwtUtils.generateAccessToken(userDetails);

        return ApiResponse.builder()
                .message("OK")
                .result(accessToken)
                .build();
    }
}
