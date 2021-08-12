package me.lozm.domain.auth.repository;

import me.lozm.domain.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {



}
