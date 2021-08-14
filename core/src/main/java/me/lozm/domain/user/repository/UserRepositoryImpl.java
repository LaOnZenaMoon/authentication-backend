package me.lozm.domain.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.lozm.domain.user.entity.User;
import me.lozm.global.code.UsersType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.lozm.domain.user.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<User> getUserListByUsersType(UsersType usersType, Pageable pageable) {
        return jpaQueryFactory
                .select(user)
                .from(user)
                .where(
                        checkUsersType(usersType)
                )
                .orderBy(user.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public long getUserTotalCountByUsersType(UsersType usersType) {
        return jpaQueryFactory
                .select(user)
                .from(user)
                .where(
                        checkUsersType(usersType)
                )
                .fetchCount();
    }

    @Override
    public BooleanExpression checkUsersType(UsersType usersType) {
        if (usersType.equals(UsersType.ALL)) {
            return null;
        }

        return user.type.eq(usersType);
    }

}
