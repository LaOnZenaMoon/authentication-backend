package me.lozm.domain.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.RoleResource;
import me.lozm.domain.auth.repository.RoleResourceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RoleResourceHelperService {

    private final RoleResourceRepository roleResourceRepository;


    public Optional<RoleResource> findRoleResource(Long roleResourceId) {
        return roleResourceRepository.findById(roleResourceId);
    }

    public RoleResource getRoleResource(Long roleResourceId) {
        return findRoleResource(roleResourceId)
                .orElseThrow(() -> new IllegalArgumentException(format("존재하지 않는 RoleResource 입니다. RoleResource ID: [%d]", roleResourceId)));
    }

}
