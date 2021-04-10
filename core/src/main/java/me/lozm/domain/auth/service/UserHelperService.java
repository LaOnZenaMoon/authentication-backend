package me.lozm.domain.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.User;
import me.lozm.domain.auth.repository.UserRepository;
import me.lozm.global.code.UseYn;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserHelperService {

    private final UserRepository userRepository;


    public Optional<User> findUser(String identifier, UseYn useYn) {
        return userRepository.findByIdentifierAndUse(identifier, useYn);
    }

    public User getUser(String identifier, UseYn useYn) {
        return findUser(identifier, useYn)
                .orElseThrow(() -> new IllegalArgumentException(String.format("존재하지 않는 사용자입니다. 사용자 계정: [%s]", identifier)));
    }

}
