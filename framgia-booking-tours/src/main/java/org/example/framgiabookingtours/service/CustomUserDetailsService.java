package org.example.framgiabookingtours.service;

import org.example.framgiabookingtours.dto.CustomUserDetails;

public interface CustomUserDetailsService {
    CustomUserDetails loadUserByUsername(String email);
}
