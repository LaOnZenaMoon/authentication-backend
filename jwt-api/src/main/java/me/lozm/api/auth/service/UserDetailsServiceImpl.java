package me.lozm.api.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.user.entity.User;
import me.lozm.domain.user.service.UserHelperService;
import me.lozm.domain.auth.vo.AuthenticationVo;
import me.lozm.global.code.UseYn;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserHelperService userHelperService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationVo authenticationVo = getAuthVoByUserIdentifier(username);

        //TODO Collection<? extends GrantedAuthority> authorities 설정
        return new org.springframework.security.core.userdetails.User(
                authenticationVo.getIdentifier(),
                authenticationVo.getPassword(),
                new ArrayList<>()
        );
    }


    private AuthenticationVo getAuthVoByUserIdentifier(String username) {
        User user = userHelperService.getUser(username, UseYn.USE);

        return AuthenticationVo.builder()
                .id(user.getId())
                .name(user.getName())
                .identifier(user.getIdentifier())
                .password(user.getPassword())
                .type(user.getType())
                .build();
    }

}
