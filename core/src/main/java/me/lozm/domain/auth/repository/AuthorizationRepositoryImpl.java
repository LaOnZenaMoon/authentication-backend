package me.lozm.domain.auth.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.*;
import me.lozm.global.object.dto.SearchDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorizationRepositoryImpl implements AuthorizationRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Role> getRoleList(Pageable pageable, SearchDto searchDto) {
        return jpaQueryFactory.selectFrom(QRole.role)
                .where(search(searchDto))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public long getRoleTotalCount(SearchDto searchDto) {
        return jpaQueryFactory.selectFrom(QRole.role)
                .where(search(searchDto))
                .fetchCount();
    }

    @Override
    public List<UserRole> getUserRoleList(Pageable pageable, SearchDto searchDto) {
        return jpaQueryFactory.selectFrom(QUserRole.userRole)
                .where(search(searchDto))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public long getUserRoleTotalCount(SearchDto searchDto) {
        return jpaQueryFactory.selectFrom(QUserRole.userRole)
                .where(search(searchDto))
                .fetchCount();
    }

    @Override
    public List<Resource> getResourceList(Pageable pageable, SearchDto searchDto) {
        return jpaQueryFactory.selectFrom(QResource.resource)
                .where(search(searchDto))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public long getResourceTotalCount(SearchDto searchDto) {
        return jpaQueryFactory.selectFrom(QResource.resource)
                .where(search(searchDto))
                .fetchCount();
    }

    @Override
    public List<RoleResource> getRoleResourceList(Pageable pageable, SearchDto searchDto) {
        return jpaQueryFactory.selectFrom(QRoleResource.roleResource)
                .where(search(searchDto))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public long getRoleResourceTotalCount(SearchDto searchDto) {
        return jpaQueryFactory.selectFrom(QRoleResource.roleResource)
                .where(search(searchDto))
                .fetchCount();
    }

    @Override
    public List<RoleHierarchy> getRoleHierarchyList(Pageable pageable, SearchDto searchDto) {
        return jpaQueryFactory.selectFrom(QRoleHierarchy.roleHierarchy)
                .where(search(searchDto))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public long getRoleHierarchyTotalCount(SearchDto searchDto) {
        return jpaQueryFactory.selectFrom(QRoleHierarchy.roleHierarchy)
                .where(search(searchDto))
                .fetchCount();
    }


    private Predicate search(SearchDto searchDto) {
        if (StringUtils.isBlank(searchDto.getSearchContent())) {
            return null;
        }

        return null;
    }

}
