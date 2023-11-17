package dev.isudha.userservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.isudha.userservice.models.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Session findByUser_Email(String email);
    Optional<Session> findByUser_Id(Long id);
    Optional<Session> findByTokenAndUser_Id(String token, Long id);
}
