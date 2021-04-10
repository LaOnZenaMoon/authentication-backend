package me.lozm.api.auth;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.User;
import me.lozm.domain.auth.service.UserHelperService;
import me.lozm.domain.auth.vo.AuthVo;
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
        AuthVo authVo = getAuthVoByUserIdentifier(username);

        //TODO Collection<? extends GrantedAuthority> authorities 설정
        return new org.springframework.security.core.userdetails.User(
                authVo.getIdentifier(),
                authVo.getPassword(),
                new ArrayList<>()
        );
    }


    private AuthVo getAuthVoByUserIdentifier(String username) {
        User user = userHelperService.getUser(username, UseYn.USE);

        return AuthVo.builder()
                .id(user.getId())
                .name(user.getName())
                .identifier(user.getIdentifier())
                .password(user.getPassword())
                .type(user.getType())
                .build();
    }

}
