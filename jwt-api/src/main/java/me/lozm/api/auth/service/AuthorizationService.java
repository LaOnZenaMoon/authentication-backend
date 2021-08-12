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
import me.lozm.object.dto.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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


    public Role addRole(AuthorizationDto.RoleRequest requestDto) {
        return roleRepository.save(Role.builder()
                .roleName(requestDto.getRoleName())
                .roleDescription(requestDto.getRoleDescription())
                .build());
    }

    public UserRole addUserRole(AuthorizationDto.UserRoleRequest requestDto) {
        final User user = userHelperService.getUser(requestDto.getUserId(), UseYn.USE);
        final Role role = roleHelperService.getRole(requestDto.getRoleId());

        return userRoleRepository.save(UserRole.builder()
                .user(user)
                .role(role)
                .build());
    }

    public Resource addResource(AuthorizationDto.ResourceRequest requestDto) {
        return resourceRepository.save(Resource.builder()
                .resourceName(requestDto.getResourceName())
                .resourceType(requestDto.getResourceType())
                .httpMethod(requestDto.getHttpMethod())
                .orderNumber(requestDto.getOrderNumber())
                .build());
    }

    public RoleResource addRoleResource(AuthorizationDto.RoleResourceRequest requestDto) {
        final Role role = roleHelperService.getRole(requestDto.getRoleId());
        final Resource resource = resourceHelperService.getResource(requestDto.getResourceId());

        return roleResourceRepository.save(RoleResource.builder()
                .role(role)
                .resource(resource)
                .build());
    }

}
