package me.lozm.api.user.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.user.dto.UserDto;
import me.lozm.domain.user.entity.User;
import me.lozm.domain.user.repository.UserRepository;
import me.lozm.domain.user.service.UserHelperService;
import me.lozm.domain.user.vo.UserVo;
import me.lozm.global.code.UseYn;
import me.lozm.global.code.UsersType;
import me.lozm.global.object.dto.SearchDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserHelperService userHelperService;
    private final PasswordEncoder passwordEncoder;


    public UserDto.UserList getUserList(UsersType usersType, Pageable pageable, SearchDto searchDto) {
        final List<UserVo.UserList> userList = userRepository.getUserListByUsersType(usersType, pageable, searchDto);
        long totalCount = userRepository.getUserTotalCountByUsersType(usersType, searchDto);
        return UserDto.UserList.createUserList(new PageImpl<>(userList, pageable, totalCount));
    }

    public UserDto.UserInfo getUserDetail(Long userId) {
        return UserDto.UserInfo.from(userHelperService.getUser(userId));
    }

    @Transactional
    public UserDto.UserInfo addUser(UserDto.AddRequest requestDto) {
        final User createdUser = userHelperService.getUser(requestDto.getCreatedBy(), UseYn.USE);

        final Optional<User> user = userHelperService.findUser(requestDto.getIdentifier(), UseYn.USE);
        if (user.isPresent()) {
            throw new IllegalArgumentException(String.format("이미 존재하는 사용자 계정입니다. 사용자 계정: [%s]", requestDto.getIdentifier()));
        }

        final User savedUser = userRepository.save(User.builder()
                .name(requestDto.getName())
                .identifier(requestDto.getIdentifier())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .type(requestDto.getType())
                .createdUser(createdUser)
                .createdDateTime(LocalDateTime.now())
                .use(UseYn.USE)
                .build());

        return UserDto.UserInfo.from(savedUser);
    }

    @Transactional
    public UserDto.UserInfo editUser(UserDto.EditRequest requestDto) {
        User user = userHelperService.getUser(requestDto.getId());
        final User modifiedUser = userHelperService.getUser(requestDto.getModifiedBy(), UseYn.USE);

        String password = requestDto.getPassword();
        String encodedPassword = StringUtils.isEmpty(password) ? null : passwordEncoder.encode(password);

        user.edit(modifiedUser,
                requestDto.getUseYn(),
                requestDto.getName(),
                encodedPassword,
                requestDto.getType()
        );
        return UserDto.UserInfo.from(user);
    }

    @Transactional
    public UserDto.UserInfo removeUser(Long userId, Long removeUserId) {
        User user = userHelperService.getUser(userId, UseYn.USE);
        final User modifiedUser = userHelperService.getUser(removeUserId, UseYn.USE);
        user.remove(modifiedUser);
        return UserDto.UserInfo.from(user);
    }

}
