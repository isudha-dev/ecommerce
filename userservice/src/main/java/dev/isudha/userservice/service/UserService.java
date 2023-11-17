package dev.isudha.userservice.service;

import org.springframework.stereotype.Service;
import dev.isudha.userservice.dto.AddRoleRequestDto;
import dev.isudha.userservice.models.User;

@Service
public interface UserService {

    // add role to user
    User addRole(AddRoleRequestDto requestDto);

    User findByEmail(String email);

    User findById(Long id);
}
