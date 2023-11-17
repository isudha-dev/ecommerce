package dev.isudha.userservice.controller;

import java.util.HashMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import dev.isudha.userservice.dto.SessionDto;
import dev.isudha.userservice.dto.UserRequestDto;
import dev.isudha.userservice.dto.UserResponseDto;
import dev.isudha.userservice.dto.UserValidateDto;
import dev.isudha.userservice.models.SessionState;
import dev.isudha.userservice.service.AuthService;
import dev.isudha.userservice.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userDto = authService.createUser(userRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody UserRequestDto userRequestDto) {
        SessionDto sessionDto = authService.loginUser(userRequestDto);

        UserResponseDto userDto = UserResponseDto.from(userService.findByEmail(userRequestDto.getEmail()));

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token: " + sessionDto.getToken());

        ResponseEntity<UserResponseDto> responseEntity = new ResponseEntity<>(userDto, headers, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestBody SessionDto session) {
        authService.logoutUser(session);
        return new ResponseEntity<>("Logout successful.", HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateUser(@RequestBody UserValidateDto userValidateDto) {
        SessionState sessionState = authService.validateToken(userValidateDto.getToken(), userValidateDto.getUserId());
        String msg = sessionState.equals(SessionState.ACTIVE) ? "Token validated successfully." : "Token not valid.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
