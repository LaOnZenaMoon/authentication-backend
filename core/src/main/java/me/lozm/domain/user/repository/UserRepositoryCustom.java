package me.lozm.domain.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import me.lozm.domain.user.entity.User;
import me.lozm.global.code.UseYn;
import me.lozm.global.code.UsersType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {

    List<User> getUserListByUsersType(UsersType usersType, Pageable pageable);

    long getUserTotalCountByUsersType(UsersType usersType);

    BooleanExpression checkUsersType(UsersType usersType);

}
