package me.lozm.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import me.lozm.global.common.BaseUserDto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AuthDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotEmpty
        private String identifier;

        @NotEmpty
        private String password;
    }

    @Getter @Builder
    static public class Response implements Serializable {
        private static final long serialVersionUID = -8091879091924046844L;
        private String token;
    }

}
