package org.example.framgiabookingtours.service;

import org.example.framgiabookingtours.dto.request.*;
import org.example.framgiabookingtours.dto.response.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);
    void register(RegisterRequestDTO registerRequestDTO);
    AuthResponseDTO verify(VerifyEmailRequestDTO verifyEmailRequestDTO);
    void resendVerificationCode(ResendOtpRequestDTO resendDTO);
    AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshDTO);
    void logout(String authHeader);
}
