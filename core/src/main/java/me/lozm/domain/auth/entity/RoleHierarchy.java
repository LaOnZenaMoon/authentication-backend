package me.lozm.domain.auth.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@DynamicInsert
@Table(schema = "LOZM", name = "AUTH_ROLE_HIERARCHY")
@SequenceGenerator(name = "AUTH_ROLE_HIERARCHY_SEQ_GEN", sequenceName = "AUTH_ROLE_HIERARCHY_SEQ", allocationSize = 1)
public class RoleHierarchy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_ROLE_HIERARCHY_SEQ_GEN")
    private Long id;

//    @Column(name = "CHILD_ROLE_ID")
//    private Long childRoleId;
//
//    @Column(name = "CHILD_ROLE_NAME")
//    private String childName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ROLE_ID")
    private Role parentRole;

//    @Column(name = "PARENT_ROLE_ID")
//    private Long parentRoleId;
//
//    @Column(name = "PARENT_ROLE_NAME")
//    private String parentName;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PARENT_ROLE_ID", referencedColumnName = "CHILD_ROLE_ID", insertable = false, updatable = false)
//    private RoleHierarchy parentRoleHierarchy;

//    @OneToMany(mappedBy = "parentRoleHierarchy", cascade={CascadeType.ALL})
//    private List<RoleHierarchy> roleHierarchies;

}
