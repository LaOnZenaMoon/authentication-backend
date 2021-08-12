package me.lozm.api.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.lozm.api.auth.service.AuthorizationService;
import me.lozm.domain.auth.dto.AuthorizationDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"인가(Authorization)"})
@CrossOrigin
@RequestMapping("authorization")
@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;


//    @ApiOperation("인가(Authorization) 데이터 조회")
//    @GetMapping
//    public AuthorizationDto.ResponseList getAuthorizationList(PageDto pageDto, SearchDto searchDto) {
//        return AuthorizationDto.ResponseList.createBoardList(authorizationService.getAuthorizationList(pageDto.getPageRequest(), searchDto));
//    }

    @ApiOperation("Role 추가")
    @PostMapping("role")
    public AuthorizationDto.RoleResponse addRole(@RequestBody @Valid AuthorizationDto.RoleRequest requestDto) {
        return AuthorizationDto.RoleResponse.from(authorizationService.addRole(requestDto));
    }

    @ApiOperation("UserRole 추가")
    @PostMapping("user-role")
    public AuthorizationDto.UserRoleResponse addUserRole(@RequestBody @Valid AuthorizationDto.UserRoleRequest requestDto) {
        return AuthorizationDto.UserRoleResponse.from(authorizationService.addUserRole(requestDto));
    }

    @ApiOperation("Resource 추가")
    @PostMapping("resource")
    public AuthorizationDto.ResourceResponse addResource(@RequestBody @Valid AuthorizationDto.ResourceRequest requestDto) {
        return AuthorizationDto.ResourceResponse.from(authorizationService.addResource(requestDto));
    }

    @ApiOperation("RoleResource 추가")
    @PostMapping("role-resource")
    public AuthorizationDto.RoleResourceResponse addRoleResource(@RequestBody @Valid AuthorizationDto.RoleResourceRequest requestDto) {
        return AuthorizationDto.RoleResourceResponse.from(authorizationService.addRoleResource(requestDto));
    }

}
