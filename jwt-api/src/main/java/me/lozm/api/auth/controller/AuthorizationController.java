package me.lozm.api.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.lozm.api.auth.service.AuthorizationService;
import me.lozm.domain.auth.dto.AuthorizationDto;
import me.lozm.global.object.dto.PageDto;
import me.lozm.global.object.dto.SearchDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"인가(Authorization)"})
@CrossOrigin
@RequestMapping("authorization")
@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;


    @ApiOperation("Role 목록 조회")
    @GetMapping("role")
    public AuthorizationDto.RoleList getRoleList(PageDto pageDto, SearchDto searchDto) {
        return authorizationService.getRoleList(pageDto.getPageRequest(), searchDto);
    }

    @ApiOperation("Role 추가")
    @PostMapping("role")
    public AuthorizationDto.RoleResponse addRole(@RequestBody @Valid AuthorizationDto.RoleRequest requestDto) {
        return AuthorizationDto.RoleResponse.from(authorizationService.addRole(requestDto));
    }

    @ApiOperation("Role 삭제")
    @DeleteMapping("role/{roleId}/user/{removeUserId}")
    public AuthorizationDto.RoleResponse removeRole(@PathVariable("roleId") Long roleId, @PathVariable("removeUserId") Long removeUserId) {
        return AuthorizationDto.RoleResponse.from(authorizationService.removeRole(roleId, removeUserId));
    }


    @ApiOperation("UserRole 목록 조회")
    @GetMapping("user-role")
    public AuthorizationDto.UserRoleList getUserRoleList(PageDto pageDto, SearchDto searchDto) {
        return authorizationService.getUserRoleList(pageDto.getPageRequest(), searchDto);
    }

    @ApiOperation("UserRole 추가")
    @PostMapping("user-role")
    public AuthorizationDto.UserRoleResponse addUserRole(@RequestBody @Valid AuthorizationDto.UserRoleRequest requestDto) {
        return AuthorizationDto.UserRoleResponse.from(authorizationService.addUserRole(requestDto));
    }

    @ApiOperation("UserRole 삭제")
    @DeleteMapping("user-role/{userRoleId}/user/{removeUserId}")
    public AuthorizationDto.UserRoleResponse removeUserRole(@PathVariable("userRoleId") Long userRoleId, @PathVariable("removeUserId") Long removeUserId) {
        return AuthorizationDto.UserRoleResponse.from(authorizationService.removeUserRole(userRoleId, removeUserId));
    }


    @ApiOperation("Resource 목록 조회")
    @GetMapping("resource")
    public AuthorizationDto.ResourceList getResourceList(PageDto pageDto, SearchDto searchDto) {
        return authorizationService.getResourceList(pageDto.getPageRequest(), searchDto);
    }

    @ApiOperation("Resource 추가")
    @PostMapping("resource")
    public AuthorizationDto.ResourceResponse addResource(@RequestBody @Valid AuthorizationDto.ResourceRequest requestDto) {
        return AuthorizationDto.ResourceResponse.from(authorizationService.addResource(requestDto));
    }

    @ApiOperation("Resource 삭제")
    @DeleteMapping("resource/{resourceId}/user/{removeUserId}")
    public AuthorizationDto.ResourceResponse removeResource(@PathVariable("resourceId") Long resourceId, @PathVariable("removeUserId") Long removeUserId) {
        return AuthorizationDto.ResourceResponse.from(authorizationService.removeResource(resourceId, removeUserId));
    }


    @ApiOperation("RoleResource 목록 조회")
    @GetMapping("role-resource")
    public AuthorizationDto.RoleResourceList getRoleResourceList(PageDto pageDto, SearchDto searchDto) {
        return authorizationService.getRoleResourceList(pageDto.getPageRequest(), searchDto);
    }

    @ApiOperation("RoleResource 추가")
    @PostMapping("role-resource")
    public AuthorizationDto.RoleResourceResponse addRoleResource(@RequestBody @Valid AuthorizationDto.RoleResourceRequest requestDto) {
        return AuthorizationDto.RoleResourceResponse.from(authorizationService.addRoleResource(requestDto));
    }

    @ApiOperation("RoleResource 삭제")
    @DeleteMapping("role-resource/{roleResourceId}/user/{removeUserId}")
    public AuthorizationDto.RoleResourceResponse removeRoleResource(@PathVariable("roleResourceId") Long roleResourceId, @PathVariable("removeUserId") Long removeUserId) {
        return AuthorizationDto.RoleResourceResponse.from(authorizationService.removeRoleResource(roleResourceId, removeUserId));
    }


    @ApiOperation("RoleHierarchy 목록 조회")
    @GetMapping("role-hierarchy")
    public AuthorizationDto.RoleHierarchyList getRoleHierarchyList(PageDto pageDto, SearchDto searchDto) {
        return authorizationService.getRoleHierarchyList(pageDto.getPageRequest(), searchDto);
    }

    @ApiOperation("RoleHierarchy 추가")
    @PostMapping("role-hierarchy")
    public AuthorizationDto.RoleHierarchyResponse addRoleHierarchy(@RequestBody @Valid AuthorizationDto.RoleHierarchyRequest requestDto) {
        return AuthorizationDto.RoleHierarchyResponse.from(authorizationService.addRoleHierarchy(requestDto));
    }

    @ApiOperation("RoleHierarchy 삭제")
    @DeleteMapping("role-hierarchy/{roleHierarchyId}/user/{removeUserId}")
    public AuthorizationDto.RoleHierarchyResponse removeRoleHierarchy(@PathVariable("roleHierarchyId") Long roleHierarchyId, @PathVariable("removeUserId") Long removeUserId) {
        return AuthorizationDto.RoleHierarchyResponse.from(authorizationService.removeRoleHierarchy(roleHierarchyId, removeUserId));
    }

}
