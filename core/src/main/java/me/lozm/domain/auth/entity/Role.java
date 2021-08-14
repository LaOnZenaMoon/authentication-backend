package me.lozm.domain.auth.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.global.object.entity.BaseEntity;

import javax.persistence.*;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(schema = "LOZM", name = "AUTH_ROLE")
@SequenceGenerator(name = "AUTH_ROLE_SEQ_GEN", sequenceName = "AUTH_ROLE_SEQ", allocationSize = 1)
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_ROLE_SEQ_GEN")
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "ROLE_DESC")
    private String roleDescription;

}
