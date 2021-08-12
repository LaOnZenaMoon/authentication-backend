package me.lozm.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.domain.auth.entity.*;
import me.lozm.global.code.ResourceType;
import me.lozm.global.object.dto.BaseUserDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AuthorizationDto {

//    @Getter
//    @Builder
//    public static class ResponseListInfo {
//
//        public static ResponseListInfo from(BoardVo.ListInfo boardInfo) {
//            return null;
//        }
//    }
//
//    @Getter
//    public static class ResponseList {
//        Page<ResponseListInfo> boardList;
//
//        public static ResponseList createBoardList(Page<BoardVo.ListInfo> boardList) {
//            ResponseList list = new ResponseList();
//            list.boardList = boardList.map(ResponseListInfo::from);
//            return list;
//        }
//    }

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
