package me.lozm.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.global.common.BaseUserDto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AuthenticationDto {

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request extends BaseUserDto {
        @NotEmpty
        private String identifier;

        @NotEmpty
        private String password;
    }

    @Getter
    @Builder
    public static class Response implements Serializable {
        private static final long serialVersionUID = -8091879091924046844L;
        private String token;
    }

}
