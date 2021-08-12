package me.lozm.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.domain.auth.entity.Resource;
import me.lozm.domain.auth.entity.Role;
import me.lozm.domain.auth.entity.RoleResource;
import me.lozm.domain.auth.entity.UserRole;
import me.lozm.global.code.ResourceType;
import me.lozm.global.code.UseYn;
import me.lozm.global.common.BaseUserDto;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    @AllArgsConstructor
    public static class RoleRequest extends BaseUserDto {
        @NotEmpty
        private String roleName;

        @NotEmpty
        private String roleDescription;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
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
    @AllArgsConstructor
    public static class UserRoleRequest extends BaseUserDto {
        @NotNull
        private Long userId;

        @NotNull
        private Long roleId;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
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
    @AllArgsConstructor
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
    @AllArgsConstructor
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
    @AllArgsConstructor
    public static class RoleResourceRequest extends BaseUserDto {
        @NotNull
        private Long roleId;

        @NotNull
        private Long resourceId;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoleResourceResponse extends RoleResourceRequest {

        private Long roleResourceId;

        public static RoleResourceResponse from(RoleResource roleResource) {
            return RoleResourceResponse.builder()
                    .roleId(roleResource.getRole().getId())
                    .resourceId(roleResource.getResource().getId())
                    .build();
        }
    }

}