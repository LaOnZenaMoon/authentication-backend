package me.lozm.domain.auth.dto;

import io.swagger.annotations.ApiModelProperty;
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

import static me.lozm.global.swagger.AuthorizationCode.*;
import static me.lozm.global.swagger.CommonCode.*;

public class AuthorizationDto {

    @Getter
    @Builder
    public static class RoleListInfo {
        @ApiModelProperty(value = ROLE_ID_DESCRIPTION)
        private Long id;

        @ApiModelProperty(value = ROLE_NAME_DESCRIPTION)
        private String roleName;

        @ApiModelProperty(value = ROLE_DESCRIPTION_DESCRIPTION)
        private String roleDescription;

        @ApiModelProperty(value = USE_YN_DESCRIPTION)
        private UseYn useYn;

        @ApiModelProperty(value = CREATED_DATETIME_DESCRIPTION)
        private LocalDateTime createdDateTime;

        @ApiModelProperty(value = CREATED_BY_DESCRIPTION)
        private Long createdUserId;

        @ApiModelProperty(value = CREATED_USER_LOGIN_ID_DESCRIPTION)
        private String createdUserIdentifier;

        @ApiModelProperty(value = MODIFIED_DATETIME_DESCRIPTION)
        private LocalDateTime modifiedDateTime;

        @ApiModelProperty(value = MODIFIED_BY_DESCRIPTION)
        private Long modifiedUserId;

        @ApiModelProperty(value = MODIFIED_USER_LOGIN_ID_DESCRIPTION)
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
        @ApiModelProperty(value = USER_ROLE_ID_DESCRIPTION)
        private Long id;

        @ApiModelProperty(value = USER_ID_DESCRIPTION)
        private Long userId;

        @ApiModelProperty(value = USER_NAME_DESCRIPTION)
        private String userName;

        @ApiModelProperty(value = ROLE_ID_DESCRIPTION)
        private Long roleId;

        @ApiModelProperty(value = ROLE_NAME_DESCRIPTION)
        private String roleName;

        @ApiModelProperty(value = ROLE_DESCRIPTION_DESCRIPTION)
        private String roleDescription;

        @ApiModelProperty(value = USE_YN_DESCRIPTION)
        private UseYn useYn;

        @ApiModelProperty(value = CREATED_DATETIME_DESCRIPTION)
        private LocalDateTime createdDateTime;

        @ApiModelProperty(value = CREATED_BY_DESCRIPTION)
        private Long createdUserId;

        @ApiModelProperty(value = CREATED_USER_LOGIN_ID_DESCRIPTION)
        private String createdUserIdentifier;

        @ApiModelProperty(value = MODIFIED_DATETIME_DESCRIPTION)
        private LocalDateTime modifiedDateTime;

        @ApiModelProperty(value = MODIFIED_BY_DESCRIPTION)
        private Long modifiedUserId;

        @ApiModelProperty(value = MODIFIED_USER_LOGIN_ID_DESCRIPTION)
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
        @ApiModelProperty(value = RESOURCE_ID_DESCRIPTION)
        private Long id;

        @ApiModelProperty(value = RESOURCE_NAME_DESCRIPTION)
        private String resourceName;

        @ApiModelProperty(value = RESOURCE_TYPE_DESCRIPTION)
        private ResourceType resourceType;

        @ApiModelProperty(value = RESOURCE_HTTP_METHOD_DESCRIPTION)
        private String httpMethod;

        @ApiModelProperty(value = RESOURCE_ORDER_NUMBER_DESCRIPTION)
        private Integer orderNumber;

        @ApiModelProperty(value = USE_YN_DESCRIPTION)
        private UseYn useYn;

        @ApiModelProperty(value = CREATED_DATETIME_DESCRIPTION)
        private LocalDateTime createdDateTime;

        @ApiModelProperty(value = CREATED_BY_DESCRIPTION)
        private Long createdUserId;

        @ApiModelProperty(value = CREATED_USER_LOGIN_ID_DESCRIPTION)
        private String createdUserIdentifier;

        @ApiModelProperty(value = MODIFIED_DATETIME_DESCRIPTION)
        private LocalDateTime modifiedDateTime;

        @ApiModelProperty(value = MODIFIED_BY_DESCRIPTION)
        private Long modifiedUserId;

        @ApiModelProperty(value = MODIFIED_USER_LOGIN_ID_DESCRIPTION)
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
        @ApiModelProperty(value = ROLE_RESOURCE_ID_DESCRIPTION)
        private Long id;

        @ApiModelProperty(value = ROLE_ID_DESCRIPTION)
        private Long roleId;

        @ApiModelProperty(value = ROLE_NAME_DESCRIPTION)
        private String roleName;

        @ApiModelProperty(value = ROLE_DESCRIPTION_DESCRIPTION)
        private String roleDescription;

        @ApiModelProperty(value = RESOURCE_ID_DESCRIPTION)
        private Long resourceId;

        @ApiModelProperty(value = RESOURCE_NAME_DESCRIPTION)
        private String resourceName;

        @ApiModelProperty(value = RESOURCE_TYPE_DESCRIPTION)
        private ResourceType resourceType;

        @ApiModelProperty(value = RESOURCE_HTTP_METHOD_DESCRIPTION)
        private String httpMethod;

        @ApiModelProperty(value = RESOURCE_ORDER_NUMBER_DESCRIPTION)
        private Integer orderNumber;

        @ApiModelProperty(value = USE_YN_DESCRIPTION)
        private UseYn useYn;

        @ApiModelProperty(value = CREATED_DATETIME_DESCRIPTION)
        private LocalDateTime createdDateTime;

        @ApiModelProperty(value = CREATED_BY_DESCRIPTION)
        private Long createdUserId;

        @ApiModelProperty(value = CREATED_USER_LOGIN_ID_DESCRIPTION)
        private String createdUserIdentifier;

        @ApiModelProperty(value = MODIFIED_DATETIME_DESCRIPTION)
        private LocalDateTime modifiedDateTime;

        @ApiModelProperty(value = MODIFIED_BY_DESCRIPTION)
        private Long modifiedUserId;

        @ApiModelProperty(value = MODIFIED_USER_LOGIN_ID_DESCRIPTION)
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
        @ApiModelProperty(value = ROLE_HIERARCHY_ID_DESCRIPTION)
        private Long id;

        @ApiModelProperty(value = ROLE_ID_DESCRIPTION)
        private Long roleId;

        @ApiModelProperty(value = ROLE_NAME_DESCRIPTION)
        private String roleName;

        @ApiModelProperty(value = ROLE_DESCRIPTION_DESCRIPTION)
        private String roleDescription;

        @ApiModelProperty(value = ROLE_ID_DESCRIPTION)
        private Long parentRoleId;

        @ApiModelProperty(value = ROLE_NAME_DESCRIPTION)
        private String parentRoleName;

        @ApiModelProperty(value = ROLE_DESCRIPTION_DESCRIPTION)
        private String parentRoleDescription;

        @ApiModelProperty(value = USE_YN_DESCRIPTION)
        private UseYn useYn;

        @ApiModelProperty(value = CREATED_DATETIME_DESCRIPTION)
        private LocalDateTime createdDateTime;

        @ApiModelProperty(value = CREATED_BY_DESCRIPTION)
        private Long createdUserId;

        @ApiModelProperty(value = CREATED_USER_LOGIN_ID_DESCRIPTION)
        private String createdUserIdentifier;

        @ApiModelProperty(value = MODIFIED_DATETIME_DESCRIPTION)
        private LocalDateTime modifiedDateTime;

        @ApiModelProperty(value = MODIFIED_BY_DESCRIPTION)
        private Long modifiedUserId;

        @ApiModelProperty(value = MODIFIED_USER_LOGIN_ID_DESCRIPTION)
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
        @ApiModelProperty(value = ROLE_NAME_DESCRIPTION, example = ROLE_NAME_EXAMPLE)
        private String roleName;

        @NotEmpty
        @ApiModelProperty(value = ROLE_DESCRIPTION_DESCRIPTION, example = ROLE_DESCRIPTION_EXAMPLE)
        private String roleDescription;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class RoleResponse extends RoleRequest {

        @ApiModelProperty(value = ROLE_ID_DESCRIPTION)
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
        @ApiModelProperty(value = USER_ID_DESCRIPTION, example = USER_ID_EXAMPLE)
        private Long userId;

        @NotNull
        @ApiModelProperty(value = ROLE_ID_DESCRIPTION, example = ROLE_ID_EXAMPLE)
        private Long roleId;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class UserRoleResponse extends UserRoleRequest {
        @ApiModelProperty(value = USER_ROLE_ID_DESCRIPTION, example = USER_ROLE_ID_EXAMPLE)
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
        @ApiModelProperty(value = RESOURCE_NAME_DESCRIPTION, example = RESOURCE_NAME_EXAMPLE)
        private String resourceName;

        @NotNull
        @ApiModelProperty(value = RESOURCE_TYPE_DESCRIPTION, example = RESOURCE_TYPE_EXAMPLE)
        private ResourceType resourceType;

        @NotEmpty
        @ApiModelProperty(value = RESOURCE_HTTP_METHOD_DESCRIPTION, example = RESOURCE_HTTP_METHOD_EXAMPLE)
        private String httpMethod;

        @NotNull
        @ApiModelProperty(value = RESOURCE_ORDER_NUMBER_DESCRIPTION, example = RESOURCE_ORDER_NUMBER_EXAMPLE)
        private Integer orderNumber;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class ResourceResponse extends ResourceRequest {

        @ApiModelProperty(value = RESOURCE_ID_DESCRIPTION)
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
        @ApiModelProperty(value = ROLE_ID_DESCRIPTION, example = ROLE_ID_EXAMPLE)
        private Long roleId;

        @NotNull
        @ApiModelProperty(value = RESOURCE_ID_DESCRIPTION, example = RESOURCE_ID_EXAMPLE)
        private Long resourceId;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class RoleResourceResponse extends RoleResourceRequest {

        @ApiModelProperty(value = ROLE_RESOURCE_ID_DESCRIPTION)
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
        @ApiModelProperty(value = ROLE_ID_DESCRIPTION, example = ROLE_ID_EXAMPLE)
        private Long roleId;

        @ApiModelProperty(value = ROLE_ID_DESCRIPTION, example = ROLE_ID_EXAMPLE)
        private Long parentRoleId;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class RoleHierarchyResponse extends RoleHierarchyRequest {
        @ApiModelProperty(value = ROLE_HIERARCHY_ID_DESCRIPTION)
        private Long roleHierarchyId;

        @ApiModelProperty(value = ROLE_NAME_DESCRIPTION)
        private String roleName;

        @ApiModelProperty(value = ROLE_NAME_DESCRIPTION)
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
