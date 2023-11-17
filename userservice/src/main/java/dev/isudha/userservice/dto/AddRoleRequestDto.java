package dev.isudha.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddRoleRequestDto {

    private Long userId;
    private String roleName;
}
