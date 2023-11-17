package dev.isudha.userservice.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import dev.isudha.userservice.dto.AddRoleRequestDto;
import dev.isudha.userservice.models.Role;
import dev.isudha.userservice.models.User;
import dev.isudha.userservice.repository.UserRepository;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override public User addRole(AddRoleRequestDto requestDto) {
        Optional<User> user1 = userRepository.findById(requestDto.getUserId());
        Set<User> users = new HashSet<>();
        users.add(user1.get());
        Role role = new Role(requestDto.getRoleName());
        Set<Role> roles = user1.get().getRoles();
        role.setName(role.getName().toLowerCase());
        if (!roles.contains(role)) {
            roles.add(role);
        }
        user1.get().setRoles(roles);
        userRepository.save(user1.get());
        return user1.get();
    }
    @Override public User findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
}
