package me.lozm.domain.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import me.lozm.domain.user.vo.UserVo;
import me.lozm.global.code.UsersType;
import me.lozm.global.object.dto.SearchDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {

    List<UserVo.UserList> getUserListByUsersType(UsersType usersType, Pageable pageable, SearchDto searchDto);

    long getUserTotalCountByUsersType(UsersType usersType, SearchDto searchDto);

    BooleanExpression checkUsersType(UsersType usersType);

}
