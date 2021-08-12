package me.lozm.api.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.dto.AuthorizationDto;
import me.lozm.domain.auth.entity.Resource;
import me.lozm.domain.auth.entity.Role;
import me.lozm.domain.auth.entity.RoleResource;
import me.lozm.domain.auth.entity.UserRole;
import me.lozm.domain.auth.repository.ResourceRepository;
import me.lozm.domain.auth.repository.RoleRepository;
import me.lozm.domain.auth.repository.RoleResourceRepository;
import me.lozm.domain.auth.repository.UserRoleRepository;
import me.lozm.domain.auth.service.ResourceHelperService;
import me.lozm.domain.auth.service.RoleHelperService;
import me.lozm.domain.user.entity.User;
import me.lozm.domain.user.service.UserHelperService;
import me.lozm.global.code.UseYn;
import me.lozm.global.security.UrlFilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional
    public Role addRole(AuthorizationDto.RoleRequest requestDto) {

        // validate RoleVoter > prefix
        if (!requestDto.getRoleName().startsWith("ROLE_")) {
            throw new IllegalArgumentException("Role 이름은 [ROLE_] 을 접미사로 사용해야 합니다.");
        }

        final Role role = roleRepository.save(Role.builder()
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
                .user(user)
                .role(role)
                .build());

        urlFilterInvocationSecurityMetadataSource.reload();

        return userRole;
    }

    @Transactional
    public Resource addResource(AuthorizationDto.ResourceRequest requestDto) {
        final Resource resource = resourceRepository.save(Resource.builder()
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
                .role(role)
                .resource(resource)
                .build());

        urlFilterInvocationSecurityMetadataSource.reload();

        return roleResource;
    }

}
