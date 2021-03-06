package me.lozm.domain.auth.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.lozm.global.code.UsersType;
import me.lozm.global.object.vo.BaseVo;

import java.io.Serializable;

@Getter
@SuperBuilder
@NoArgsConstructor
public class AuthenticationVo extends BaseVo implements Serializable {

    private static final long serialVersionUID = 1000100010000000001L;

    private Long id;
    private String name;
    private String identifier;
    private String password;
    private UsersType type;
    @Setter
    private String token;

}
