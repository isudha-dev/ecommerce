package dev.isudha.userservice.dto;

import lombok.Data;

@Data
public class UserValidateDto {

    private Long userId;
    private String token;
}
