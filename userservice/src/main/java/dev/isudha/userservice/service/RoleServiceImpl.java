package dev.isudha.userservice.service;

import java.util.HashSet;
import org.springframework.stereotype.Service;
import dev.isudha.userservice.models.Role;
import dev.isudha.userservice.repository.RoleRepository;

@Service("RoleService")
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    public Role createRole(String name) {
        Role role = roleRepository.save(new Role(name));
        return role;
    }
}
