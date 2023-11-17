package dev.isudha.userservice.dto;

import java.util.HashSet;
import java.util.Set;
import dev.isudha.userservice.models.Role;
import dev.isudha.userservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String email;
    private Set<Role> roles = new HashSet<>();

    public static UserResponseDto from(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setUserId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
