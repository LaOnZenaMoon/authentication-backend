package me.lozm.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lozm.domain.auth.entity.RoleHierarchy;
import me.lozm.domain.auth.repository.RoleHierarchyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleHierarchyHelperService {

    private final RoleHierarchyRepository roleHierarchyRepository;


    @Transactional(readOnly = true)
    public String findAllHierarchy() {
        List<RoleHierarchy> rolesHierarchy = roleHierarchyRepository.findAll();

        Iterator<RoleHierarchy> itr = rolesHierarchy.iterator();
        StringBuilder concatedRoles = new StringBuilder();
        while (itr.hasNext()) {
            RoleHierarchy roleHierarchy = itr.next();

            if (roleHierarchy.getParentRole() != null) {
                concatedRoles.append(roleHierarchy.getParentRole().getRoleName());
                concatedRoles.append(" > ");
                concatedRoles.append(roleHierarchy.getRole().getRoleName());
                concatedRoles.append("\n");
            }
        }

        log.info(String.format("Role Hierarchy: [%s]", concatedRoles));
        return concatedRoles.toString();
    }

}
