package me.lozm.domain.auth.repository;

import me.lozm.domain.auth.entity.User;
import me.lozm.global.code.UseYn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdentifierAndUse(String identifier, UseYn use);

}
