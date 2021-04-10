package me.lozm.api.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.dto.AuthDto;
import me.lozm.domain.auth.vo.AuthVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"JWT 인증"})
@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final JwtAuthenticationService jwtAuthenticationService;


    @ApiOperation("JWT 인증토큰 발급")
    @PostMapping("authenticate")
    public ResponseEntity createAuthenticationToken(@RequestBody AuthDto.Request reqDto) {
        AuthVo jwt = jwtAuthenticationService.getToken(AuthVo.builder()
                .identifier(reqDto.getIdentifier())
                .password(reqDto.getPassword())
                .build());

        return ResponseEntity.ok(AuthDto.Response.builder()
                .token(jwt.getToken())
                .build());
    }

}
