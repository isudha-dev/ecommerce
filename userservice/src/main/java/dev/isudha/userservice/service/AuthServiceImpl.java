package dev.isudha.userservice.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import dev.isudha.userservice.dto.SessionDto;
import dev.isudha.userservice.dto.UserRequestDto;
import dev.isudha.userservice.dto.UserResponseDto;
import dev.isudha.userservice.models.Session;
import dev.isudha.userservice.models.SessionState;
import dev.isudha.userservice.models.User;
import dev.isudha.userservice.repository.SessionRepository;
import dev.isudha.userservice.repository.UserRepository;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {

    private final SessionRepository sessionRepository;
    private UserRepository userRepo;
    private SessionService sessionService;
    private BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository ur, SessionService ss, BCryptPasswordEncoder pe,
        final SessionRepository sessionRepository) {
        this.userRepo = ur;
        this.sessionService = ss;
        this.passwordEncoder = pe;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public UserResponseDto createUser(final UserRequestDto userdto) {
        String encodedPassword = passwordEncoder.encode(userdto.getPassword());
        User user = new User(userdto.getEmail(), encodedPassword);
        userRepo.save(user);
        UserResponseDto userDto = new UserResponseDto(user.getId(), user.getEmail(), null);
        return userDto;
    }
    @Override
    public SessionDto loginUser(final UserRequestDto user) {
        User user1 = userRepo.findByEmail(user.getEmail());
        if (user1 == null) {
            // you can create new user if user does not exist
            // createUser(new UserRequestDto(user.getEmail(), user.getPassword()));
            // return loginUser(user);
            throw new RuntimeException("Authentication service: User not found");
        }

        String dbPassword = user1.getPassword();
        if (!passwordEncoder.matches(user.getPassword(), dbPassword)) {
            throw new RuntimeException("Authentication service: Password does not match");
        }

        Session session = sessionService.searchSession(user1.getEmail());
        if (session == null || !session.getState().equals(SessionState.ACTIVE))
            session = sessionService.createSession(user1.getEmail());
        SessionDto sessionDto = SessionDto.from(session);
        return sessionDto;
    }
    @Override
    public void logoutUser(SessionDto sessionDto) {
        sessionService.clearSession(sessionDto);
    }

    @Override
    public SessionState validateToken(String token, Long userId) {
        Session session = sessionService.validateSession(new SessionDto(userId, token));
        return session.getState();
    }

    /*
        why JWT?
        Without JWT there needs to be 2 calls
            1. To check if token is valid
            2. To get details about user needed to proceed
        With JWT
            Additional user info details can be hidden in JWT token which can be used by clients
     */
}
