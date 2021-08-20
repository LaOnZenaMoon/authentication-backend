package me.lozm.api.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.dto.AuthorizationDto;
import me.lozm.domain.auth.entity.*;
import me.lozm.domain.auth.repository.*;
import me.lozm.domain.auth.service.*;
import me.lozm.domain.user.entity.User;
import me.lozm.domain.user.service.UserHelperService;
import me.lozm.global.code.UseYn;
import me.lozm.global.object.dto.SearchDto;
import me.lozm.global.security.UrlFilterInvocationSecurityMetadataSource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static me.lozm.global.config.CommonConfig.ROLE_NAME_PREFIX;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorizationService {

    private final RoleRepository roleRepository;
    private final RoleHelperService roleHelperService;
    private final UserHelperService userHelperService;
    private final UserRoleRepository userRoleRepository;
    private final UserRoleHelperService userRoleHelperService;
    private final ResourceRepository resourceRepository;
    private final ResourceHelperService resourceHelperService;
    private final RoleResourceRepository roleResourceRepository;
    private final RoleResourceHelperService roleResourceHelperService;
    private final RoleHierarchyRepository roleHierarchyRepository;
    private final RoleHierarchyHelperService roleHierarchyHelperService;
    private final AuthorizationRepository authorizationRepository;
    private final UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;


    public AuthorizationDto.RoleList getRoleList(Pageable pageable, SearchDto searchDto) {
        List<Role> roleList = authorizationRepository.getRoleList(pageable, searchDto);
        long totalCount = authorizationRepository.getRoleTotalCount(searchDto);
        return AuthorizationDto.RoleList.createList(new PageImpl<>(roleList, pageable, totalCount));
    }

    @Transactional
    public Role addRole(AuthorizationDto.RoleRequest requestDto) {
        // validate RoleVoter > prefix
        if (!requestDto.getRoleName().startsWith(ROLE_NAME_PREFIX)) {
            throw new IllegalArgumentException(format("Role 이름은 [%s] 을 접미사로 사용해야 합니다.", ROLE_NAME_PREFIX));
        }

        final Role role = roleRepository.save(Role.builder()
                .createdDateTime(LocalDateTime.now())
                .use(UseYn.USE)
                .roleName(requestDto.getRoleName())
                .roleDescription(requestDto.getRoleDescription())
                .build());

        urlFilterInvocationSecurityMetadataSource.reload();

        return role;
    }

    @Transactional
    public Role removeRole(Long roleId, Long removeUserId) {
        final Role role = roleHelperService.getRole(roleId);
        roleRepository.delete(role);
        return role;
    }


    public AuthorizationDto.UserRoleList getUserRoleList(Pageable pageable, SearchDto searchDto) {
        List<UserRole> userRoleList = authorizationRepository.getUserRoleList(pageable, searchDto);
        long totalCount = authorizationRepository.getUserRoleTotalCount(searchDto);
        return AuthorizationDto.UserRoleList.createList(new PageImpl<>(userRoleList, pageable, totalCount));
    }

    @Transactional
    public UserRole addUserRole(AuthorizationDto.UserRoleRequest requestDto) {
        final User user = userHelperService.getUser(requestDto.getUserId(), UseYn.USE);
        final Role role = roleHelperService.getRole(requestDto.getRoleId());

        final UserRole userRole = userRoleRepository.save(UserRole.builder()
                .createdDateTime(LocalDateTime.now())
                .use(UseYn.USE)
                .user(user)
                .role(role)
                .build());

        urlFilterInvocationSecurityMetadataSource.reload();

        return userRole;
    }

    @Transactional
    public UserRole removeUserRole(Long userRoleId, Long removeUserId) {
        final UserRole userRole = userRoleHelperService.getUserRole(userRoleId);
        userRoleRepository.delete(userRole);
        return userRole;
    }


    public AuthorizationDto.ResourceList getResourceList(Pageable pageable, SearchDto searchDto) {
        List<Resource> resourceList = authorizationRepository.getResourceList(pageable, searchDto);
        long totalCount = authorizationRepository.getResourceTotalCount(searchDto);
        return AuthorizationDto.ResourceList.createList(new PageImpl<>(resourceList, pageable, totalCount));
    }

    @Transactional
    public Resource addResource(AuthorizationDto.ResourceRequest requestDto) {
        final Resource resource = resourceRepository.save(Resource.builder()
                .createdDateTime(LocalDateTime.now())
                .use(UseYn.USE)
                .resourceName(requestDto.getResourceName())
                .resourceType(requestDto.getResourceType())
                .httpMethod(requestDto.getHttpMethod())
                .orderNumber(requestDto.getOrderNumber())
                .build());

        urlFilterInvocationSecurityMetadataSource.reload();

        return resource;
    }

    @Transactional
    public Resource removeResource(Long resourceId, Long removeUserId) {
        final Resource resource = resourceHelperService.getResource(resourceId);
        resourceRepository.delete(resource);
        return resource;
    }


    public AuthorizationDto.RoleResourceList getRoleResourceList(Pageable pageable, SearchDto searchDto) {
        List<RoleResource> roleResourceList = authorizationRepository.getRoleResourceList(pageable, searchDto);
        long totalCount = authorizationRepository.getRoleResourceTotalCount(searchDto);
        return AuthorizationDto.RoleResourceList.createList(new PageImpl<>(roleResourceList, pageable, totalCount));
    }

    @Transactional
    public RoleResource addRoleResource(AuthorizationDto.RoleResourceRequest requestDto) {
        final Role role = roleHelperService.getRole(requestDto.getRoleId());
        final Resource resource = resourceHelperService.getResource(requestDto.getResourceId());

        final RoleResource roleResource = roleResourceRepository.save(RoleResource.builder()
                .createdDateTime(LocalDateTime.now())
                .use(UseYn.USE)
                .role(role)
                .resource(resource)
                .build());

        urlFilterInvocationSecurityMetadataSource.reload();

        return roleResource;
    }

    @Transactional
    public RoleResource removeRoleResource(Long roleResourceId, Long removeUserId) {
        final RoleResource roleResource = roleResourceHelperService.getRoleResource(roleResourceId);
        roleResourceRepository.delete(roleResource);
        return roleResource;
    }


    public AuthorizationDto.RoleHierarchyList getRoleHierarchyList(Pageable pageable, SearchDto searchDto) {
        List<RoleHierarchy> roleResourceList = authorizationRepository.getRoleHierarchyList(pageable, searchDto);
        long totalCount = authorizationRepository.getRoleHierarchyTotalCount(searchDto);
        return AuthorizationDto.RoleHierarchyList.createList(new PageImpl<>(roleResourceList, pageable, totalCount));
    }

    @Transactional
    public RoleHierarchy addRoleHierarchy(AuthorizationDto.RoleHierarchyRequest requestDto) {
        final Role role = roleHelperService.getRole(requestDto.getRoleId());

        Role parentRole = null;
        if (isNotEmpty(requestDto.getParentRoleId())) {
            parentRole = roleHelperService.getRole(requestDto.getParentRoleId());
        }

        final Optional<RoleHierarchy> byRoleAndParentRole = roleHierarchyRepository.findByRoleAndParentRole(role, parentRole);
        if (byRoleAndParentRole.isPresent()) {
            String parentRoleName = parentRole == null ? null : parentRole.getRoleName();
            throw new IllegalArgumentException(String.format("이미 존재하는 RoleHierarchy 입니다. Role 이름: [%s], Parent Role 이름: [%s]",
                    role.getRoleName(), parentRoleName));
        }

        final RoleHierarchy roleHierarchy = roleHierarchyRepository.save(RoleHierarchy.builder()
                .createdDateTime(LocalDateTime.now())
                .use(UseYn.USE)
                .role(role)
                .parentRole(parentRole)
                .build());

        return roleHierarchy;
    }

    @Transactional
    public RoleHierarchy removeRoleHierarchy(Long roleHierarchyId, Long removeUserId) {
        final RoleHierarchy roleHierarchy = roleHierarchyHelperService.getRoleHierarchy(roleHierarchyId);
        roleHierarchyRepository.delete(roleHierarchy);
        return roleHierarchy;
    }

}
