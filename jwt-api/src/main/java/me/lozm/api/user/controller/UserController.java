package me.lozm.api.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.lozm.api.user.service.UserService;
import me.lozm.domain.user.dto.UserDto;
import me.lozm.global.code.UsersType;
import me.lozm.global.object.dto.SearchDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"사용자 관리"})
@CrossOrigin
@RequestMapping(UserController.USER_PATH)
@RestController
@RequiredArgsConstructor
public class UserController {

    public static final String USER_PATH = "/user";


    private final UserService userService;


    @ApiOperation("사용자 목록 조회")
    @GetMapping("userType/{usersType}")
    public UserDto.UserList getUserList(@PathVariable("usersType") UsersType usersType, Pageable pageable, SearchDto searchDto) {
        return userService.getUserList(usersType, pageable, searchDto);
    }

    @ApiOperation("사용자 상세 조회")
    @GetMapping("{userId}")
    public UserDto.UserInfo getUserDetail(@PathVariable("userId") Long UserId) {
        return userService.getUserDetail(UserId);
    }

    @ApiOperation("사용자 추가")
    @PostMapping
    public UserDto.UserInfo addUser(@RequestBody @Valid UserDto.AddRequest requestDto) {
        return userService.addUser(requestDto);
    }

    @ApiOperation("사용자 수정")
    @PutMapping("{userId}")
    public UserDto.UserInfo editUser(@PathVariable("userId") Long userId, @RequestBody @Valid UserDto.EditRequest requestDto) {
        return userService.editUser(requestDto);
    }

    @ApiOperation("사용자 삭제")
    @DeleteMapping("{userId}/user/{removeUserId}")
    public UserDto.UserInfo removeUser(@PathVariable("userId") Long userId, @PathVariable("removeUserId") Long removeUserId) {
        return userService.removeUser(userId, removeUserId);
    }

}
