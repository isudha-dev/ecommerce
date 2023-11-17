package dev.isudha.userservice.service;

import org.springframework.stereotype.Service;
import dev.isudha.userservice.dto.SessionDto;
import dev.isudha.userservice.dto.UserRequestDto;
import dev.isudha.userservice.dto.UserResponseDto;
import dev.isudha.userservice.models.SessionState;

@Service
public interface AuthService {

    UserResponseDto createUser(UserRequestDto user);
    SessionDto loginUser(UserRequestDto user);
    void logoutUser(SessionDto session);
    SessionState validateToken(String token, Long userId);
}
