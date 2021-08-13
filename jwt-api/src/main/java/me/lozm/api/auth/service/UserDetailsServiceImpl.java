package me.lozm.api.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.UserRole;
import me.lozm.domain.auth.repository.UserRoleRepository;
import me.lozm.domain.auth.vo.AuthenticationVo;
import me.lozm.domain.user.entity.User;
import me.lozm.domain.user.service.UserHelperService;
import me.lozm.global.code.UseYn;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserHelperService userHelperService;
    private final UserRoleRepository userRoleRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userHelperService.getUser(username, UseYn.USE);

        final AuthenticationVo authenticationVo = AuthenticationVo.builder()
                .id(user.getId())
                .name(user.getName())
                .identifier(user.getIdentifier())
                .password(user.getPassword())
                .type(user.getType())
                .build();

        List<GrantedAuthority> authorities = new ArrayList<>();
        final List<UserRole> userRoles = userRoleRepository.findByUser(user);
        for (UserRole userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole().getRoleName()));
        }

        return new org.springframework.security.core.userdetails.User(
                authenticationVo.getIdentifier(),
                authenticationVo.getPassword(),
                authorities
        );
    }

}
