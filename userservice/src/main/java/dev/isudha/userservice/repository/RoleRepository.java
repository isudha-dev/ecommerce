package dev.isudha.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.isudha.userservice.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
