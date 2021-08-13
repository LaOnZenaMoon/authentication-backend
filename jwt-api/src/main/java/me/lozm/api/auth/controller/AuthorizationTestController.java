package me.lozm.api.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"인가(Authorization) 테스트"})
@RequestMapping("authorization")
@RestController
public class AuthorizationTestController {

    @ApiOperation("ROLE_ADMIN 인가 테스트")
    @GetMapping("admin/test")
    public ResponseEntity getRoleAdminTest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("ROLE_MANAGER 인가 테스트")
    @GetMapping("manager/test")
    public ResponseEntity getRoleManagerTest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("ROLE_USER 인가 테스트")
    @GetMapping("user/test")
    public ResponseEntity getRoleUserTest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("ROLE_GUEST 인가 테스트")
    @GetMapping("guest/test")
    public ResponseEntity getRoleGuestTest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
