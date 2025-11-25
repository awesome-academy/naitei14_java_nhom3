package org.example.framgiabookingtours.service;

import org.example.framgiabookingtours.dto.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
    CustomUserDetails loadUserByUsername(String email);
}
