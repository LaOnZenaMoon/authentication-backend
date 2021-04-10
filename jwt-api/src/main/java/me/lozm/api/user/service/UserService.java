package me.lozm.api.user.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.user.dto.UserDto;
import me.lozm.domain.user.entity.User;
import me.lozm.domain.user.repository.UserRepository;
import me.lozm.domain.user.repository.UserRepositorySupport;
import me.lozm.domain.user.service.UserHelperService;
import me.lozm.global.code.UseYn;
import me.lozm.global.code.UsersType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserHelperService userHelperService;
    private final UserRepositorySupport userRepositorySupport;
    private final PasswordEncoder passwordEncoder;


    public Page<User> getUserList(UsersType usersType, Pageable pageable) {
        List<User> userList = userRepositorySupport.getUserListByUsersType(usersType, pageable);
        long totalCount = userRepositorySupport.getUserTotalCountByUsersType(usersType);
        return new PageImpl<>(userList, pageable, totalCount);
    }

    public User getUserDetail(Long userId) {

        return userHelperService.getUser(userId, UseYn.USE);
    }

    @Transactional
    public User addUser(UserDto.AddRequest requestDto) {

        return userRepository.save(User.builder()
                .name(requestDto.getName())
                .identifier(requestDto.getIdentifier())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .type(requestDto.getType())
                .createdBy(requestDto.getCreatedBy())
                .use(UseYn.USE)
                .build());
    }

    @Transactional
    public User editUser(UserDto.EditRequest requestDto) {

        User user = userHelperService.getUser(requestDto.getId(), UseYn.USE);

        String password = requestDto.getPassword();
        String encodedPassword = StringUtils.isEmpty(password) ? null : passwordEncoder.encode(password);

        user.edit(requestDto.getName(), encodedPassword, requestDto.getType(), requestDto.getModifiedBy(), UseYn.USE);
        return user;
    }

    @Transactional
    public User removeUser(UserDto.RemoveRequest requestDto) {

        User user = userHelperService.getUser(requestDto.getId(), UseYn.USE);
        user.edit(null, null, null, requestDto.getModifiedBy(), UseYn.NOT_USE);
        return user;
    }

}
