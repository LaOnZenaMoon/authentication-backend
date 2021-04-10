package me.lozm.global.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class AuthInfo {

    public AuthInfo(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    private static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";
    private static final String AUTHORIZATION = "Authorization";
    private static JwtTokenUtils jwtTokenUtils;


    private static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    private static String getHttpServletRequestValueByHeader(String headerName) {
        return getHttpServletRequest().getHeader(headerName);
    }

    public static String getClientIp() {
        if (getHttpServletRequestValueByHeader(X_FORWARDED_FOR) != null) {
            return getHttpServletRequestValueByHeader(X_FORWARDED_FOR);
        }
        return getHttpServletRequest().getRemoteAddr();
    }

    private static String getBearerToken(String requestTokenHeader) {
        String token = getHttpServletRequestValueByHeader(requestTokenHeader);
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    public static String getUserId() {
        try {
            return jwtTokenUtils.getUsernameFromToken(getBearerToken(AUTHORIZATION));
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (ExpiredJwtException e) {
            throw e;
        }
    }

    public static Long getId() {
        try {
            return jwtTokenUtils.getId(getBearerToken(AUTHORIZATION));
        } catch (Exception e) {
            throw e;
        }
    }

}
