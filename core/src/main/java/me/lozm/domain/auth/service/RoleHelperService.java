package me.lozm.domain.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.Role;
import me.lozm.domain.auth.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RoleHelperService {

    private final RoleRepository roleRepository;


    public Optional<Role> findRole(Long roleId) {
        return roleRepository.findById(roleId);
    }

    public Role getRole(Long roleId) {
        return findRole(roleId)
                .orElseThrow(() -> new IllegalArgumentException(format("존재하지 않는 Role 입니다. Role ID: [%d]", roleId)));
    }

}
