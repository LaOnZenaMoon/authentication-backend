package me.lozm.api.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"인가(Authorization) 테스트"})
@CrossOrigin
@RequestMapping("authorization/test")
@RestController
public class AuthorizationTestController {

    @ApiOperation("ROLE_ADMIN 인가 테스트")
    @GetMapping("role-admin")
    public void getRoleAdminTest() {
    }

    @ApiOperation("ROLE_MANAGER 인가 테스트")
    @GetMapping("role-manager")
    public void getRoleManagerTest() {
    }

    @ApiOperation("ROLE_USER 인가 테스트")
    @GetMapping("role-user")
    public void getRoleUserTest() {
    }

    @ApiOperation("ROLE_GUEST 인가 테스트")
    @GetMapping("role-guest")
    public void getRoleGuestTest() {
    }

}
