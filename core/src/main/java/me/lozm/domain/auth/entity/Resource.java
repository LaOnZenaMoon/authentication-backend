package me.lozm.domain.auth.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.global.code.ResourceType;
import me.lozm.global.code.converter.ResourceTypeConverter;
import me.lozm.global.object.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(schema = "LOZM", name = "AUTH_RESOURCE")
@SequenceGenerator(name = "AUTH_RESOURCE_SEQ_GEN", sequenceName = "AUTH_RESOURCE_SEQ", allocationSize = 1)
public class Resource extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_RESOURCE_SEQ_GEN")
    @Column(name = "RESOURCE_ID")
    private Long id;

    @Column(name = "RESOURCE_NAME")
    private String resourceName;

    @Column(name = "RESOURCE_TYPE")
    @Convert(converter = ResourceTypeConverter.class)
    private ResourceType resourceType;

    @Column(name = "HTTP_METHOD")
    private String httpMethod;

    @Column(name = "ORDER_NUM")
    private Integer orderNumber;

    @OneToMany(mappedBy = "resource")
    private List<RoleResource> roleResources;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "ROLE_RESOURCES", joinColumns = {@JoinColumn(name = "RESOURCE_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
//    private Set<Role> roleSet = new HashSet<>();

}
