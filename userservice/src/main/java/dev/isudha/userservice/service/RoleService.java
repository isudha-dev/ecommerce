package dev.isudha.userservice.service;

import org.springframework.stereotype.Service;
import dev.isudha.userservice.models.Role;

@Service
public interface RoleService {
    Role createRole(String name);
}
