package me.lozm.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.domain.auth.entity.*;
import me.lozm.domain.user.entity.User;
import me.lozm.global.code.ResourceType;
import me.lozm.global.code.UseYn;
import me.lozm.global.code.UsersType;
import me.lozm.global.object.dto.BaseUserDto;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AuthorizationDto {

    @Getter
    @Builder
    public static class RoleListInfo {
        private Long id;
        private String roleName;
        private String roleDescription;
        private UseYn useYn;
        private LocalDateTime createdDateTime;
        private Long createdUserId;
        private String createdUserIdentifier;
        private LocalDateTime modifiedDateTime;
        private Long modifiedUserId;
        private String modifiedUserIdentifier;

        public static RoleListInfo from(Role entity) {
            User createdUser;
            if (entity.getCreatedUser() == null) {
                createdUser = User.builder().build();
            } else if (entity.getCreatedUser().getId().equals(UsersType.SYSTEM.getCode())) {
                createdUser = User.from(UsersType.SYSTEM);
            } else {
                createdUser = entity.getCreatedUser();
            }

            User modifiedUser;
            if (entity.getModifiedUser() == null) {
                modifiedUser = User.builder().build();
            } else {
                modifiedUser = entity.getModifiedUser();
            }

            return RoleListInfo.builder()
                    .id(entity.getId())
                    .roleName(entity.getRoleName())
                    .roleDescription(entity.getRoleDescription())
                    .useYn(entity.getUse())
                    .createdDateTime(entity.getCreatedDateTime())
                    .createdUserId(createdUser.getId())
                    .createdUserIdentifier(createdUser.getIdentifier())
                    .modifiedDateTime(entity.getModifiedDateTime())
                    .modifiedUserId(modifiedUser.getId())
                    .modifiedUserIdentifier(modifiedUser.getIdentifier())
                    .build();
        }
    }

    @Getter
    public static class RoleList {
        Page<RoleListInfo> roleList;

        public static AuthorizationDto.RoleList createList(Page<Role> entities) {
            AuthorizationDto.RoleList list = new AuthorizationDto.RoleList();
            list.roleList = entities.map(AuthorizationDto.RoleListInfo::from);
            return list;
        }
    }


    @Getter
    @Builder
    public static class UserRoleListInfo {
        private Long id;
        private Long userId;
        private String userName;
        private Long roleId;
        private String roleName;
        private String roleDescription;
        private UseYn useYn;
        private LocalDateTime createdDateTime;
        private Long createdUserId;
        private String createdUserIdentifier;
        private LocalDateTime modifiedDateTime;
        private Long modifiedUserId;
        private String modifiedUserIdentifier;

        public static UserRoleListInfo from(UserRole entity) {
            User createdUser;
            if (entity.getCreatedUser() == null) {
                createdUser = User.builder().build();
            } else if (entity.getCreatedUser().getId().equals(UsersType.SYSTEM.getCode())) {
                createdUser = User.from(UsersType.SYSTEM);
            } else {
                createdUser = entity.getCreatedUser();
            }

            User modifiedUser;
            if (entity.getModifiedUser() == null) {
                modifiedUser = User.builder().build();
            } else {
                modifiedUser = entity.getModifiedUser();
            }

            return UserRoleListInfo.builder()
                    .id(entity.getId())
                    .userId(entity.getUser().getId())
                    .userName(entity.getUser().getName())
                    .roleId(entity.getRole().getId())
                    .roleName(entity.getRole().getRoleName())
                    .roleDescription(entity.getRole().getRoleDescription())
                    .useYn(entity.getUse())
                    .createdDateTime(entity.getCreatedDateTime())
                    .createdUserId(createdUser.getId())
                    .createdUserIdentifier(createdUser.getIdentifier())
                    .modifiedDateTime(entity.getModifiedDateTime())
                    .modifiedUserId(modifiedUser.getId())
                    .modifiedUserIdentifier(modifiedUser.getIdentifier())
                    .build();
        }
    }

    @Getter
    public static class UserRoleList {
        Page<UserRoleListInfo> userRoleList;

        public static AuthorizationDto.UserRoleList createList(Page<UserRole> entities) {
            AuthorizationDto.UserRoleList list = new AuthorizationDto.UserRoleList();
            list.userRoleList = entities.map(AuthorizationDto.UserRoleListInfo::from);
            return list;
        }
    }


    @Getter
    @Builder
    public static class ResourceListInfo {
        private Long id;
        private String resourceName;
        private ResourceType resourceType;
        private String httpMethod;
        private Integer orderNumber;
        private UseYn useYn;
        private LocalDateTime createdDateTime;
        private Long createdUserId;
        private String createdUserIdentifier;
        private LocalDateTime modifiedDateTime;
        private Long modifiedUserId;
        private String modifiedUserIdentifier;

        public static ResourceListInfo from(Resource entity) {
            User createdUser;
            if (entity.getCreatedUser() == null) {
                createdUser = User.builder().build();
            } else if (entity.getCreatedUser().getId().equals(UsersType.SYSTEM.getCode())) {
                createdUser = User.from(UsersType.SYSTEM);
            } else {
                createdUser = entity.getCreatedUser();
            }

            User modifiedUser;
            if (entity.getModifiedUser() == null) {
                modifiedUser = User.builder().build();
            } else {
                modifiedUser = entity.getModifiedUser();
            }

            return ResourceListInfo.builder()
                    .id(entity.getId())
                    .resourceName(entity.getResourceName())
                    .resourceType(entity.getResourceType())
                    .httpMethod(entity.getHttpMethod())
                    .orderNumber(entity.getOrderNumber())
                    .useYn(entity.getUse())
                    .createdDateTime(entity.getCreatedDateTime())
                    .createdUserId(createdUser.getId())
                    .createdUserIdentifier(createdUser.getIdentifier())
                    .modifiedDateTime(entity.getModifiedDateTime())
                    .modifiedUserId(modifiedUser.getId())
                    .modifiedUserIdentifier(modifiedUser.getIdentifier())
                    .build();
        }
    }

    @Getter
    public static class ResourceList {
        Page<ResourceListInfo> resourceList;

        public static AuthorizationDto.ResourceList createList(Page<Resource> entities) {
            AuthorizationDto.ResourceList list = new AuthorizationDto.ResourceList();
            list.resourceList = entities.map(AuthorizationDto.ResourceListInfo::from);
            return list;
        }
    }


    @Getter
    @Builder
    public static class RoleResourceListInfo {
        private Long id;
        private Long roleId;
        private String roleName;
        private String roleDescription;
        private Long resourceId;
        private String resourceName;
        private ResourceType resourceType;
        private String httpMethod;
        private Integer orderNumber;
        private UseYn useYn;
        private LocalDateTime createdDateTime;
        private Long createdUserId;
        private String createdUserIdentifier;
        private LocalDateTime modifiedDateTime;
        private Long modifiedUserId;
        private String modifiedUserIdentifier;

        public static RoleResourceListInfo from(RoleResource entity) {
            User createdUser;
            if (entity.getCreatedUser() == null) {
                createdUser = User.builder().build();
            } else if (entity.getCreatedUser().getId().equals(UsersType.SYSTEM.getCode())) {
                createdUser = User.from(UsersType.SYSTEM);
            } else {
                createdUser = entity.getCreatedUser();
            }

            User modifiedUser;
            if (entity.getModifiedUser() == null) {
                modifiedUser = User.builder().build();
            } else {
                modifiedUser = entity.getModifiedUser();
            }

            return RoleResourceListInfo.builder()
                    .id(entity.getId())
                    .roleId(entity.getRole().getId())
                    .roleName(entity.getRole().getRoleName())
                    .roleDescription(entity.getRole().getRoleDescription())
                    .resourceId(entity.getResource().getId())
                    .resourceName(entity.getResource().getResourceName())
                    .resourceType(entity.getResource().getResourceType())
                    .httpMethod(entity.getResource().getHttpMethod())
                    .orderNumber(entity.getResource().getOrderNumber())
                    .useYn(entity.getUse())
                    .createdDateTime(entity.getCreatedDateTime())
                    .createdUserId(createdUser.getId())
                    .createdUserIdentifier(createdUser.getIdentifier())
                    .modifiedDateTime(entity.getModifiedDateTime())
                    .modifiedUserId(modifiedUser.getId())
                    .modifiedUserIdentifier(modifiedUser.getIdentifier())
                    .build();
        }
    }

    @Getter
    public static class RoleResourceList {
        Page<RoleResourceListInfo> roleResourceList;

        public static AuthorizationDto.RoleResourceList createList(Page<RoleResource> entities) {
            AuthorizationDto.RoleResourceList list = new AuthorizationDto.RoleResourceList();
            list.roleResourceList = entities.map(AuthorizationDto.RoleResourceListInfo::from);
            return list;
        }
    }


    @Getter
    @Builder
    public static class RoleHierarchyListInfo {
        private Long id;
        private Long roleId;
        private String roleName;
        private String roleDescription;
        private Long parentRoleId;
        private String parentRoleName;
        private String parentRoleDescription;
        private UseYn useYn;
        private LocalDateTime createdDateTime;
        private Long createdUserId;
        private String createdUserIdentifier;
        private LocalDateTime modifiedDateTime;
        private Long modifiedUserId;
        private String modifiedUserIdentifier;

        public static RoleHierarchyListInfo from(RoleHierarchy entity) {
            User createdUser;
            if (entity.getCreatedUser() == null) {
                createdUser = User.builder().build();
            } else if (entity.getCreatedUser().getId().equals(UsersType.SYSTEM.getCode())) {
                createdUser = User.from(UsersType.SYSTEM);
            } else {
                createdUser = entity.getCreatedUser();
            }

            User modifiedUser;
            if (entity.getModifiedUser() == null) {
                modifiedUser = User.builder().build();
            } else {
                modifiedUser = entity.getModifiedUser();
            }

            final Role parentRole = entity.getParentRole() == null ? Role.builder().build() : entity.getParentRole();

            return RoleHierarchyListInfo.builder()
                    .id(entity.getId())
                    .roleId(entity.getRole().getId())
                    .roleName(entity.getRole().getRoleName())
                    .roleDescription(entity.getRole().getRoleDescription())
                    .parentRoleId(parentRole.getId())
                    .parentRoleName(parentRole.getRoleName())
                    .parentRoleDescription(parentRole.getRoleDescription())
                    .useYn(entity.getUse())
                    .createdDateTime(entity.getCreatedDateTime())
                    .createdUserId(createdUser.getId())
                    .createdUserIdentifier(createdUser.getIdentifier())
                    .modifiedDateTime(entity.getModifiedDateTime())
                    .modifiedUserId(modifiedUser.getId())
                    .modifiedUserIdentifier(modifiedUser.getIdentifier())
                    .build();
        }
    }

    @Getter
    public static class RoleHierarchyList {
        Page<RoleHierarchyListInfo> roleHierarchyList;

        public static AuthorizationDto.RoleHierarchyList createList(Page<RoleHierarchy> entities) {
            AuthorizationDto.RoleHierarchyList list = new AuthorizationDto.RoleHierarchyList();
            list.roleHierarchyList = entities.map(AuthorizationDto.RoleHierarchyListInfo::from);
            return list;
        }
    }


    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class RoleRequest extends BaseUserDto {
        @NotEmpty
        private String roleName;

        @NotEmpty
        private String roleDescription;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class RoleResponse extends RoleRequest {

        private Long roleId;

        public static RoleResponse from(Role role) {
            return RoleResponse.builder()
                    .roleId(role.getId())
                    .roleName(role.getRoleName())
                    .roleDescription(role.getRoleDescription())
                    .build();
        }
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class UserRoleRequest extends BaseUserDto {
        @NotNull
        private Long userId;

        @NotNull
        private Long roleId;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class UserRoleResponse extends UserRoleRequest {

        private Long userRoleId;

        public static UserRoleResponse from(UserRole userRole) {
            return UserRoleResponse.builder()
                    .userRoleId(userRole.getId())
                    .userId(userRole.getUser().getId())
                    .roleId(userRole.getRole().getId())
                    .build();
        }

    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class ResourceRequest extends BaseUserDto {
        @NotEmpty
        private String resourceName;

        @NotNull
        private ResourceType resourceType;

        @NotEmpty
        private String httpMethod;

        @NotNull
        private Integer orderNumber;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class ResourceResponse extends ResourceRequest {

        private Long resourceId;

        public static ResourceResponse from(Resource resource) {
            return ResourceResponse.builder()
                    .resourceId(resource.getId())
                    .resourceName(resource.getResourceName())
                    .resourceType(resource.getResourceType())
                    .httpMethod(resource.getHttpMethod())
                    .orderNumber(resource.getOrderNumber())
                    .build();
        }
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class RoleResourceRequest extends BaseUserDto {
        @NotNull
        private Long roleId;

        @NotNull
        private Long resourceId;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class RoleResourceResponse extends RoleResourceRequest {

        private Long roleResourceId;

        public static RoleResourceResponse from(RoleResource roleResource) {
            return RoleResourceResponse.builder()
                    .roleId(roleResource.getRole().getId())
                    .resourceId(roleResource.getResource().getId())
                    .build();
        }
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class RoleHierarchyRequest extends BaseUserDto {
        @NotNull
        private Long roleId;

        private Long parentRoleId;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class RoleHierarchyResponse extends RoleHierarchyRequest {

        private Long roleHierarchyId;
        private String roleName;
        private String parentRoleName;


        public static RoleHierarchyResponse from(RoleHierarchy roleHierarchy) {
            return RoleHierarchyResponse.builder()
                    .roleHierarchyId(roleHierarchy.getId())
                    .roleId(roleHierarchy.getRole().getId())
                    .roleName(roleHierarchy.getRole().getRoleName())
                    .parentRoleId(roleHierarchy.getParentRole().getId())
                    .parentRoleName(roleHierarchy.getParentRole().getRoleName())
                    .build();
        }
    }

}
