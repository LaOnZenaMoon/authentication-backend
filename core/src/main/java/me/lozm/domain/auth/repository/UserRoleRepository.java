package me.lozm.domain.auth.repository;

import me.lozm.domain.auth.entity.Role;
import me.lozm.domain.auth.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {



}
