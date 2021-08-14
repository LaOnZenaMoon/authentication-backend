package me.lozm.api.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.lozm.api.auth.service.JwtAuthenticationService;
import me.lozm.domain.auth.dto.AuthenticationDto;
import me.lozm.domain.auth.vo.AuthenticationVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"JWT 인증"})
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    public static final String AUTHENTICATE_PATH = "/authentication";


    private final JwtAuthenticationService jwtAuthenticationService;


    @ApiOperation("JWT 인증토큰 발급")
    @PostMapping(AUTHENTICATE_PATH)
    public AuthenticationDto.Response createAuthenticationToken(@RequestBody @Valid AuthenticationDto.Request requestDto) {

        AuthenticationVo jwt = jwtAuthenticationService.getToken(AuthenticationVo.builder()
                .identifier(requestDto.getIdentifier())
                .password(requestDto.getPassword())
                .build());

        return AuthenticationDto.Response.builder()
                .token(jwt.getToken())
                .build();
    }

}
