package dev.isudha.userservice.service;

import org.springframework.stereotype.Service;
import dev.isudha.userservice.models.Session;
import dev.isudha.userservice.dto.SessionDto;

@Service
public interface SessionService {

    Session createSession(String userEmail);

    Session validateSession(SessionDto session);

    void clearSession(SessionDto sessionDto);

    Session searchSession(String userEmail);

}
