package me.lozm.api.authentication;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.dto.AuthenticationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("naver")
@RestController
@RequiredArgsConstructor
public class NaverAuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("sign-in")
    public void signIn(@RequestBody @Valid AuthenticationDto.Request requestDto) {

    }


}
