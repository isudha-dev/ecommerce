package dev.isudha.userservice.dto;

import dev.isudha.userservice.models.Session;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {

    private Long userId;
    private String token;

    public static SessionDto from(Session session) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.token = session.getToken();
        sessionDto.userId = session.getUser().getId();
        return sessionDto;
    }
}
