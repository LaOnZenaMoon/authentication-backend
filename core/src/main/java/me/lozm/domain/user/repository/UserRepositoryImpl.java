package me.lozm.domain.user.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.lozm.domain.user.entity.QUser;
import me.lozm.domain.user.vo.UserVo;
import me.lozm.global.code.SearchType;
import me.lozm.global.code.UsersType;
import me.lozm.global.object.dto.SearchDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.lozm.domain.user.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<UserVo.UserList> getUserListByUsersType(UsersType usersType, Pageable pageable, SearchDto searchDto) {
        final QUser createdUser = new QUser("createdUser");
        final QUser modifiedUser = new QUser("modifiedUser");

        return jpaQueryFactory
                .select(Projections.fields(
                        UserVo.UserList.class,
                        user.id.as("userId"),
                        user.name.as("name"),
                        user.identifier.as("identifier"),
                        user.type.as("type"),
                        user.use.as("userUse"),
                        user.createdDateTime.as("userCreatedDateTime"),
                        user.createdUser.id.as("createdUserId"),
                        user.createdUser.identifier.as("createdUserIdentifier"),
                        user.modifiedDateTime.as("userModifiedDateTime"),
                        user.modifiedUser.id.as("modifiedUserId"),
                        user.modifiedUser.identifier.as("modifiedUserIdentifier")
                ))
                .from(user)
                .leftJoin(user.createdUser, createdUser)
                .leftJoin(user.modifiedUser, modifiedUser)
                .where(
                        checkUsersType(usersType),
                        search(searchDto)
                )
                .orderBy(user.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public long getUserTotalCountByUsersType(UsersType usersType, SearchDto searchDto) {
        return jpaQueryFactory
                .select(user)
                .from(user)
                .where(
                        checkUsersType(usersType),
                        search(searchDto)
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

    private Predicate search(SearchDto searchDto) {
        if (StringUtils.isBlank(searchDto.getSearchContent())) {
            return null;
        }

        if (searchDto.getSearchType() == SearchType.IDENTIFIER) {
            return user.identifier.like("%" + searchDto.getSearchContent() + "%");
        }

        return null;
    }

}
