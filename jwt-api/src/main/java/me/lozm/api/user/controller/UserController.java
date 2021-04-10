package me.lozm.api.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.lozm.api.user.service.UserService;
import me.lozm.domain.user.dto.UserDto;
import me.lozm.global.code.UsersType;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"사용자 관리"})
@RequestMapping("user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @ApiOperation("사용자 목록 조회")
    @GetMapping("userType/{usersType}")
    public UserDto.ResponseList getUserList(@PathVariable(value = "usersType") UsersType usersType, Pageable pageable) {

        return UserDto.ResponseList.createUserList(userService.getUserList(usersType, pageable));
    }

    @ApiOperation("사용자 상세 조회")
    @GetMapping("{userId}")
    public UserDto.ResponseOne getUserDetail(@PathVariable(value = "userId") Long UserId) {

        return UserDto.ResponseOne.from(userService.getUserDetail(UserId));
    }

    @ApiOperation("사용자 추가")
    @PostMapping
    public UserDto.ResponseOne addUser(@RequestBody @Valid UserDto.AddRequest requestDto) {

        return UserDto.ResponseOne.from(userService.addUser(requestDto));
    }

    @ApiOperation("사용자 수정")
    @PutMapping
    public UserDto.ResponseOne editUser(@RequestBody @Valid UserDto.EditRequest requestDto) {

        return UserDto.ResponseOne.from(userService.editUser(requestDto));
    }

    @ApiOperation("사용자 삭제")
    @DeleteMapping
    public UserDto.ResponseOne removeUser(@RequestBody @Valid UserDto.RemoveRequest requestDto) {

        return UserDto.ResponseOne.from(userService.removeUser(requestDto));
    }

}
