package me.lozm.api.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.User;
import me.lozm.domain.auth.service.UserHelperService;
import me.lozm.domain.auth.vo.AuthVo;
import me.lozm.global.code.UseYn;
import me.lozm.global.jwt.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JwtAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserHelperService userHelperService;


    public AuthVo getToken(AuthVo authVo) {
        authenticate(authVo.getIdentifier(), authVo.getPassword());
        AuthVo userInfo = getAuthVoByUserIdentifier(authVo.getIdentifier());
        userInfo.setToken(jwtTokenUtils.generateToken(userInfo));
        return userInfo;
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

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new RuntimeException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("INVALID_CREDENTIALS", e);
        }
    }

}
