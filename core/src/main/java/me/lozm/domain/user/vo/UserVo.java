package me.lozm.domain.user.vo;

import lombok.Getter;
import me.lozm.global.code.UseYn;
import me.lozm.global.code.UsersType;

import java.time.LocalDateTime;

public class UserVo {

    @Getter
    public static class UserList {
        private Long userId;
        private String name;
        private String identifier;
        private UsersType type;
        private UseYn userUse;
        private LocalDateTime userCreatedDateTime;
        private Long createdUserId;
        private String createdUserIdentifier;
        private LocalDateTime userModifiedDateTime;
        private Long modifiedUserId;
        private String modifiedUserIdentifier;
    }

}
