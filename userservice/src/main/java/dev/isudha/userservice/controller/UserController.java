package dev.isudha.userservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import dev.isudha.userservice.dto.AddRoleRequestDto;
import dev.isudha.userservice.dto.SessionDto;
import dev.isudha.userservice.dto.UserResponseDto;
import dev.isudha.userservice.models.Session;
import dev.isudha.userservice.models.SessionState;
import dev.isudha.userservice.models.User;
import dev.isudha.userservice.service.SessionService;
import dev.isudha.userservice.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private SessionService sessionService;

    public UserController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUserDetails(@PathVariable Long id) {
        User user = userService.findById(id);
        return null;
    }

    @PostMapping("/roles")
    public ResponseEntity<UserResponseDto> addRole(@RequestBody AddRoleRequestDto requestDto, @RequestHeader HttpHeaders headers) {
        String token = headers.getFirst("auth-token");
        if (token.isEmpty()) {
            throw new RuntimeException("Auth token missing in headers");
        }

        Session session = sessionService.validateSession(new SessionDto(requestDto.getUserId(), token));
        if (session == null) {
            throw new RuntimeException("User controller: Invalid session");
        } else if (session.getState().equals(SessionState.EXPIRED)) {
            throw new RuntimeException("User controller: Session expired");
        }

        User user = userService.addRole(requestDto);
        UserResponseDto userDto = UserResponseDto.from(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
