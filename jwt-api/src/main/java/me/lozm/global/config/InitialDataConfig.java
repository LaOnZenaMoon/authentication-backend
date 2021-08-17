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
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "lozm.data")
@ConditionalOnMissingClass
public class InitialDataConfig {

    private final AccessIpRepository accessIpRepository;
    private final UserService userService;
    private final UserHelperService userHelperService;
    private final AuthorizationService authorizationService;
    private final RoleHelperService roleHelperService;
    private final ResourceHelperService resourceHelperService;


    private final int DATA_SIZE_LIMIT = 2000;


    private boolean enabled = false;
    private int size = 100;


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setSize(int size) {
        if (size > DATA_SIZE_LIMIT) {
            log.warn(String.format("Data cannot be initialized because the size is too large. The size limit is %d", DATA_SIZE_LIMIT));
        }

        this.size = size;
    }

    @PostConstruct
    public void init() {
        if (!isEnabled()) {
            return;
        }

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
                getResource("/user/**", 1),
                getResource("/community-api/board", 2),
                getResource("/community-api/comment", 3)
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
        try {
            authorizationService.addRoleResource(AuthorizationDto.RoleResourceRequest.builder()
                    .roleId(roleList.get(0).getId())
                    .resourceId(resourceList.get(0).getId())
                    .build());
        } catch (RuntimeException e) {
        }

        try {
            authorizationService.addRoleResource(AuthorizationDto.RoleResourceRequest.builder()
                    .roleId(roleList.get(2).getId())
                    .resourceId(resourceList.get(1).getId())
                    .build());
        } catch (RuntimeException e) {
        }

        try {
            authorizationService.addRoleResource(AuthorizationDto.RoleResourceRequest.builder()
                    .roleId(roleList.get(3).getId())
                    .resourceId(resourceList.get(2).getId())
                    .build());
        } catch (RuntimeException e) {
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
