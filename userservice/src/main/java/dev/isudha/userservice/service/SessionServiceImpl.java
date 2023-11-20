package dev.isudha.userservice.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;
import dev.isudha.userservice.dto.SessionDto;
import dev.isudha.userservice.models.Session;
import dev.isudha.userservice.models.SessionState;
import dev.isudha.userservice.models.User;
import dev.isudha.userservice.repository.SessionRepository;
import dev.isudha.userservice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;

@Service("SessionService")
public class SessionServiceImpl implements SessionService {

    private SecretKey key;
    private SessionRepository sessionRepo;
    private UserRepository userRepository;
    private MacAlgorithm alg;

    public SessionServiceImpl(SessionRepository sr, UserRepository ur) {
        this.sessionRepo = sr;
        this.userRepository = ur;
        // Create a test key suitable for the desired HMAC-SHA algorithm:
        alg = Jwts.SIG.HS256; //or HS384 or HS256
        key = alg.key().build();
    }

    @Override
    public Session createSession(String userEmail) {
        //        String token = RandomStringUtils.randomAlphabetic(32);
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User does not exist");
        }
        User user = userOptional.get();
        String token = generateToken(user);

        Session newSession = new Session(token, user, SessionState.ACTIVE);
        sessionRepo.save(newSession);
        return newSession;
    }
    @Override
    public Session validateSession(SessionDto sessionDto) {
        Optional<Session> session = sessionRepo.findByTokenAndUser_Id(sessionDto.getToken(), sessionDto.getUserId());
        if (session.isEmpty()) {
            throw new RuntimeException("Session service: No valid session exists for given userid");
        }
        if (session.get().getState().equals(SessionState.ENDED) || session.get().getState().equals(SessionState.ENDED)) {
            throw new RuntimeException("Session service: Session expired for given userid, please login again.");
        }

        // reading claims from JWT
        Jws<Claims> claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(session.get().getToken());
        String email = (String) claims.getPayload().get("email");
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Session service: User does not exist for give email");
        }
        User user = userOptional.get();
        boolean validUserId = user.getId().equals(sessionDto.getUserId());

        Integer expiryDate = (Integer) claims.getPayload().get("expiryAt");
        Long currentDate = LocalDate.now().toEpochDay();
        boolean expiryReached = expiryDate < currentDate;

        if (!validUserId || expiryReached) {
            throw new RuntimeException("Session service: JWT could not be verified.");
        }
        return session.get();
    }
    @Override
    public void clearSession(SessionDto sessionDto) {
        Session session = validateSession(sessionDto);
        session.setState(SessionState.ENDED);
        sessionRepo.save(session);
    }
    @Override public Session searchSession(String userEmail) {
        return sessionRepo.findByUser_Email(userEmail);
    }

    private String generateToken(User user) {

        Map<String, Object> jsonForJwt = new HashMap<>();
        jsonForJwt.put("email", user.getEmail());
        jsonForJwt.put("roles", user.getRoles());
        jsonForJwt.put("createdAt", new Date());
        jsonForJwt.put("expiryAt", LocalDate.now().plusDays(3).toEpochDay());
        String token = Jwts.builder()
            .claims(jsonForJwt)
            .signWith(key, alg)
            .compact();

        //[B@364623 [B@3a033f0c
        //        String message = "Hello World";
        // user_id, user_email,
        //        byte[] content = message.getBytes(StandardCharsets.UTF_8);

        // Create the compact JWS:
        //        String jws = Jwts.builder().content(content, "text/plain").signWith(key, alg).compact();

        // Parse the compact JWS:
        //        content = Jwts.parser().verifyWith(key).build().parseSignedContent(jws).getPayload();

        return token;
    }
}
