package me.lozm.domain.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.global.object.dto.BaseUserDto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AuthenticationDto {

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class Request extends BaseUserDto {
        @NotEmpty
        @ApiModelProperty(value = "사용자 로그인 ID", example = "admin")
        private String identifier;

        @NotEmpty
        @ApiModelProperty(value = "사용자 로그인 패스워드", example = "asdfasdf1234")
        private String password;
    }

    @Getter
    @Builder
    public static class Response implements Serializable {
        private static final long serialVersionUID = -8091879091924046844L;
        private String token;
    }

}
