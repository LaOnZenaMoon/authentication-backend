package me.lozm.domain.auth.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.global.object.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@DynamicInsert
@Table(schema = "LOZM", name = "AUTH_ROLE_HIERARCHY")
@SequenceGenerator(name = "AUTH_ROLE_HIERARCHY_SEQ_GEN", sequenceName = "AUTH_ROLE_HIERARCHY_SEQ", allocationSize = 1)
public class RoleHierarchy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_ROLE_HIERARCHY_SEQ_GEN")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ROLE_ID")
    private Role parentRole;

}
