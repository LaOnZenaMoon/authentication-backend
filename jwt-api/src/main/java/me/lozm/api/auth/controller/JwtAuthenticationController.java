package me.lozm.api.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.lozm.api.auth.service.JwtAuthenticationService;
import me.lozm.domain.auth.dto.AuthDto;
import me.lozm.domain.auth.vo.AuthVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"JWT 인증"})
@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    public static final String AUTHENTICATE = "/authenticate";


    private final JwtAuthenticationService jwtAuthenticationService;


    @ApiOperation("JWT 인증토큰 발급")
    @PostMapping(AUTHENTICATE)
    public AuthDto.Response createAuthenticationToken(@RequestBody @Valid AuthDto.Request requestDto) {

        AuthVo jwt = jwtAuthenticationService.getToken(AuthVo.builder()
                .identifier(requestDto.getIdentifier())
                .password(requestDto.getPassword())
                .build());

        return AuthDto.Response.builder()
                .token(jwt.getToken())
                .build();
    }

}
