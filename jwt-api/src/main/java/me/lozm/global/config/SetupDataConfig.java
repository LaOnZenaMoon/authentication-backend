package me.lozm.global.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lozm.api.auth.service.AuthorizationService;
import me.lozm.api.user.service.UserService;
import me.lozm.domain.auth.dto.AuthorizationDto;
import me.lozm.domain.auth.entity.AccessIp;
import me.lozm.domain.auth.entity.Resource;
import me.lozm.domain.auth.entity.Role;
import me.lozm.domain.auth.repository.AccessIpRepository;
import me.lozm.domain.auth.service.ResourceHelperService;
import me.lozm.domain.auth.service.RoleHelperService;
import me.lozm.domain.user.dto.UserDto;
import me.lozm.domain.user.entity.User;
import me.lozm.domain.user.service.UserHelperService;
import me.lozm.global.code.ResourceType;
import me.lozm.global.code.UseYn;
import me.lozm.global.code.UsersType;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SetupDataConfig implements ApplicationListener<ContextRefreshedEvent> {

    private final AccessIpRepository accessIpRepository;
    private final UserService userService;
    private final UserHelperService userHelperService;
    private final AuthorizationService authorizationService;
    private final RoleHelperService roleHelperService;
    private final ResourceHelperService resourceHelperService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        final List<User> userList = setupUserData();
        final List<Role> roleList = setupRoleData();
        final List<Resource> resourceList = setupResourceData();
        setupUserRoleData(userList, roleList);
        setupRoleResourceData(roleList, resourceList);
        setupRoleHierarchyData(roleList);
        setupAccessIpData();
    }

    private List<User> setupUserData() {
        return Arrays.asList(
                getUser("admin", "관리자", UsersType.ADMIN),
                getUser("manager", "매니저", UsersType.MANAGER),
                getUser("user", "사용자", UsersType.MANAGER),
                getUser("guest", "방문자", UsersType.MANAGER)
        );
    }

    private User getUser(String identifier, String userName, UsersType usersType) {
        User admin;
        try {
            admin = userService.addUser(UserDto.AddRequest.builder()
                    .name(userName)
                    .type(usersType)
                    .identifier(identifier)
                    .password("asdfasdf1234")
                    .build());
        } catch (RuntimeException e) {
            log.debug(e.getMessage());
            admin = userHelperService.getUser(identifier, UseYn.USE);
        }
        return admin;
    }

    private List<Role> setupRoleData() {
        return Arrays.asList(
                getRole("ROLE_ADMIN"),
                getRole("ROLE_MANAGER"),
                getRole("ROLE_USER"),
                getRole("ROLE_GUEST")
        );
    }

    private Role getRole(String adminRoleName) {
        Role adminRole;
        try {
            adminRole = authorizationService.addRole(AuthorizationDto.RoleRequest.builder()
                    .roleName(adminRoleName)
                    .roleDescription(adminRoleName + " 권한입니다.")
                    .build());
        } catch (RuntimeException e) {
            log.debug(e.getMessage());
            adminRole = roleHelperService.getRole(adminRoleName);
        }
        return adminRole;
    }

    private List<Resource> setupResourceData() {
        return Arrays.asList(
                getResource("/**/admin/**", 1),
                getResource("/**/manager/**", 2),
                getResource("/**/user/**", 3),
                getResource("/**/guest/**", 4)
        );
    }

    private Resource getResource(String resourceName, Integer orderNumber) {
        Resource adminResource;
        try {
            adminResource = authorizationService.addResource(AuthorizationDto.ResourceRequest.builder()
                    .resourceName(resourceName)
                    .resourceType(ResourceType.URL)
                    .httpMethod("*")
                    .orderNumber(orderNumber)
                    .build());
        } catch (RuntimeException e) {
            log.debug(e.getMessage());
            adminResource = resourceHelperService.getResource(resourceName);
        }
        return adminResource;
    }

    private void setupUserRoleData(List<User> userList, List<Role> roleList) {
        for (int i = 0; i < userList.size(); i++) {
            try {
                authorizationService.addUserRole(AuthorizationDto.UserRoleRequest.builder()
                        .userId(userList.get(i).getId())
                        .roleId(roleList.get(i).getId())
                        .build());
            } catch (RuntimeException e) {
            }
        }
    }

    private void setupRoleResourceData(List<Role> roleList, List<Resource> resourceList) {
        for (int i = 0; i < roleList.size(); i++) {
            try {
                authorizationService.addRoleResource(AuthorizationDto.RoleResourceRequest.builder()
                        .roleId(roleList.get(i).getId())
                        .resourceId(resourceList.get(i).getId())
                        .build());
            } catch (RuntimeException e) {
            }
        }
    }

    private void setupRoleHierarchyData(List<Role> roleList) {
        for (int i = 0; i < roleList.size(); i++) {
            try {
                if (i == 0) {
                    authorizationService.addRoleHierarchy(AuthorizationDto.RoleHierarchyRequest.builder()
                            .roleId(roleList.get(i).getId())
                            .build());
                } else {
                    authorizationService.addRoleHierarchy(AuthorizationDto.RoleHierarchyRequest.builder()
                            .roleId(roleList.get(i).getId())
                            .parentRoleId(roleList.get(i - 1).getId())
                            .build());
                }
            } catch (RuntimeException e) {
            }
        }
    }

    private void setupAccessIpData() {
        final String LOCAL_HOST = "127.0.0.1";
        Optional<AccessIp> accessIp = accessIpRepository.findByIpAddressAndUse(LOCAL_HOST, UseYn.USE);
        if (!accessIp.isPresent()) {
            accessIpRepository.save(AccessIp.builder()
                    .createdBy(UsersType.SYSTEM.getCode())
                    .createdDateTime(LocalDateTime.now())
                    .use(UseYn.USE)
                    .ipAddress(LOCAL_HOST)
                    .build());
        }
    }

}
