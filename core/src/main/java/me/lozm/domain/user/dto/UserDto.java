package me.lozm.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.domain.user.entity.User;
import me.lozm.domain.user.vo.UserVo;
import me.lozm.global.code.UseYn;
import me.lozm.global.code.UsersType;
import me.lozm.global.object.dto.BaseUserDto;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UserDto {

    @Getter
    @Builder
    public static class UserListInfo {
        private Long id;
        private String name;
        private String identifier;
        private UsersType type;
        private UseYn useYn;
        private LocalDateTime createdDateTime;
        private Long createdUserId;
        private String createdUserIdentifier;
        private LocalDateTime modifiedDateTime;
        private Long modifiedUserId;
        private String modifiedUserIdentifier;

        public static UserListInfo from(UserVo.UserList user) {
            return UserListInfo.builder()
                    .id(user.getUserId())
                    .name(user.getName())
                    .identifier(user.getIdentifier())
                    .type(user.getType())
                    .useYn(user.getUserUse())
                    .createdDateTime(user.getUserCreatedDateTime())
                    .createdUserId(user.getCreatedUserId())
                    .createdUserIdentifier(user.getCreatedUserIdentifier())
                    .modifiedDateTime(user.getUserModifiedDateTime())
                    .modifiedUserId(user.getModifiedUserId())
                    .modifiedUserIdentifier(user.getModifiedUserIdentifier())
                    .build();
        }
    }

    @Getter
    public static class UserList {
        Page<UserListInfo> userList;

        public static UserList createUserList(Page<UserVo.UserList> userList) {
            UserList list = new UserList();
            list.userList = userList.map(UserListInfo::from);
            return list;
        }
    }

    @Getter
    @Builder
    public static class UserInfo {
        private Long id;
        private String name;
        private String identifier;
        private UsersType type;
        private UseYn useYn;
        private LocalDateTime createdDateTime;
        private Long createdUserId;
        private String createdUserIdentifier;
        private LocalDateTime modifiedDateTime;
        private Long modifiedUserId;
        private String modifiedUserIdentifier;

        public static UserInfo from(User user) {
            User createdUser;
            if (user.getCreatedUser() == null) {
                createdUser = User.builder().build();
            } else if (user.getCreatedUser().getId().equals(UsersType.SYSTEM.getCode())) {
                createdUser = User.from(UsersType.SYSTEM);
            } else {
                createdUser = user.getCreatedUser();
            }

            User modifiedUser;
            if (user.getModifiedUser() == null) {
                modifiedUser = User.builder().build();
            } else {
                modifiedUser = user.getModifiedUser().getId().equals(UsersType.SYSTEM.getCode()) ? User.from(UsersType.SYSTEM) : user.getModifiedUser();
            }

            return UserInfo.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .identifier(user.getIdentifier())
                    .type(user.getType())
                    .useYn(user.getUse())
                    .createdDateTime(user.getCreatedDateTime())
                    .createdUserId(createdUser.getId())
                    .createdUserIdentifier(createdUser.getIdentifier())
                    .modifiedDateTime(user.getModifiedDateTime())
                    .modifiedUserId(modifiedUser.getId())
                    .modifiedUserIdentifier(modifiedUser.getIdentifier())
                    .build();
        }
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class AddRequest extends BaseUserDto {
        @NotNull
        private String name;

        @NotNull
        private String identifier;

        @NotNull
        private String password;

        @NotNull
        private UsersType type;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class EditRequest extends BaseUserDto {
        @NotNull
        private Long id;

        private String name;
        private String password;
        private UsersType type;
        private UseYn useYn;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class RemoveRequest extends BaseUserDto {
        @NotNull
        private Long id;
    }

}
