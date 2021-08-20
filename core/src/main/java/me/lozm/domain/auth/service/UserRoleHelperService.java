package me.lozm.domain.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.UserRole;
import me.lozm.domain.auth.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserRoleHelperService {

    private final UserRoleRepository userRoleRepository;


    public Optional<UserRole> findRole(Long userRoleId) {
        return userRoleRepository.findById(userRoleId);
    }

    public UserRole getUserRole(Long userRoleId) {
        return findRole(userRoleId)
                .orElseThrow(() -> new IllegalArgumentException(format("존재하지 않는 UserRole 입니다. UserRole ID: [%d]", userRoleId)));
    }

}
