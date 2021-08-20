package me.lozm.domain.auth.repository;

import me.lozm.domain.auth.entity.*;
import me.lozm.global.object.dto.SearchDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorizationRepository {
    List<Role> getRoleList(Pageable pageable, SearchDto searchDto);

    long getRoleTotalCount(SearchDto searchDto);

    List<UserRole> getUserRoleList(Pageable pageable, SearchDto searchDto);

    long getUserRoleTotalCount(SearchDto searchDto);

    List<Resource> getResourceList(Pageable pageable, SearchDto searchDto);

    long getResourceTotalCount(SearchDto searchDto);

    List<RoleResource> getRoleResourceList(Pageable pageable, SearchDto searchDto);

    long getRoleResourceTotalCount(SearchDto searchDto);

    List<RoleHierarchy> getRoleHierarchyList(Pageable pageable, SearchDto searchDto);

    long getRoleHierarchyTotalCount(SearchDto searchDto);
}
