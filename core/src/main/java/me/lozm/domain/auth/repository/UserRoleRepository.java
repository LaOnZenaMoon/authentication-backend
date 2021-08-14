package me.lozm.domain.auth.repository;

import me.lozm.domain.auth.entity.UserRole;
import me.lozm.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUser(User user);

}
