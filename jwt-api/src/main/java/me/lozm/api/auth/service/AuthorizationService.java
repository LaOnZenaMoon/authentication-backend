package me.lozm.api.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.dto.AuthorizationDto;
import me.lozm.domain.auth.entity.*;
import me.lozm.domain.auth.repository.*;
import me.lozm.domain.auth.service.ResourceHelperService;
import me.lozm.domain.auth.service.RoleHelperService;
import me.lozm.domain.user.entity.User;
import me.lozm.domain.user.service.UserHelperService;
import me.lozm.global.code.UseYn;
import me.lozm.global.security.UrlFilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.lang.String.format;
import static me.lozm.global.config.CommonConfig.ROLE_NAME_PREFIX;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final RoleRepository roleRepository;
    private final RoleHelperService roleHelperService;
    private final UserHelperService userHelperService;
    private final UserRoleRepository userRoleRepository;
    private final ResourceRepository resourceRepository;
    private final ResourceHelperService resourceHelperService;
    private final RoleResourceRepository roleResourceRepository;
    private final UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    private final RoleHierarchyRepository roleHierarchyRepository;


    @Transactional
    public Role addRole(AuthorizationDto.RoleRequest requestDto) {

        // validate RoleVoter > prefix
        if (!requestDto.getRoleName().startsWith(ROLE_NAME_PREFIX)) {
            throw new IllegalArgumentException(format("Role 이름은 [%s] 을 접미사로 사용해야 합니다.", ROLE_NAME_PREFIX));
        }

        final Role role = roleRepository.save(Role.builder()
                .createdBy(requestDto.getCreatedBy())
                .createdDateTime(LocalDateTime.now())
                .use(UseYn.USE)
                .roleName(requestDto.getRoleName())
                .roleDescription(requestDto.getRoleDescription())
                .build());

        urlFilterInvocationSecurityMetadataSource.reload();

        return role;
    }

    @Transactional
    public UserRole addUserRole(AuthorizationDto.UserRoleRequest requestDto) {
        final User user = userHelperService.getUser(requestDto.getUserId(), UseYn.USE);
        final Role role = roleHelperService.getRole(requestDto.getRoleId());

        final UserRole userRole = userRoleRepository.save(UserRole.builder()
                .createdBy(requestDto.getCreatedBy())
                .createdDateTime(LocalDateTime.now())
                .use(UseYn.USE)
                .user(user)
                .role(role)
                .build());

        urlFilterInvocationSecurityMetadataSource.reload();

        return userRole;
    }

    @Transactional
    public Resource addResource(AuthorizationDto.ResourceRequest requestDto) {
        final Resource resource = resourceRepository.save(Resource.builder()
                .createdBy(requestDto.getCreatedBy())
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
    public RoleResource addRoleResource(AuthorizationDto.RoleResourceRequest requestDto) {
        final Role role = roleHelperService.getRole(requestDto.getRoleId());
        final Resource resource = resourceHelperService.getResource(requestDto.getResourceId());

        final RoleResource roleResource = roleResourceRepository.save(RoleResource.builder()
                .createdBy(requestDto.getCreatedBy())
                .createdDateTime(LocalDateTime.now())
                .use(UseYn.USE)
                .role(role)
                .resource(resource)
                .build());

        urlFilterInvocationSecurityMetadataSource.reload();

        return roleResource;
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
                .createdBy(requestDto.getCreatedBy())
                .createdDateTime(LocalDateTime.now())
                .use(UseYn.USE)
                .role(role)
                .parentRole(parentRole)
                .build());

        return roleHierarchy;
    }

}
