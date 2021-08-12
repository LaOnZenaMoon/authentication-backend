package me.lozm.domain.auth.repository;

import me.lozm.domain.auth.entity.Role;
import me.lozm.domain.auth.entity.RoleHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {

    Optional<RoleHierarchy> findByRoleAndParentRole(Role role, Role parentRole);

//    RoleHierarchy findByChildName(String roleName);

}
