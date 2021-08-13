package me.lozm.domain.auth.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.domain.user.entity.User;
import me.lozm.global.object.entity.BaseEntity;

import javax.persistence.*;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(schema = "LOZM", name = "AUTH_USERS_ROLE")
@SequenceGenerator(name = "AUTH_USERS_ROLE_SEQ_GEN", sequenceName = "AUTH_USERS_ROLE_SEQ", allocationSize = 1)
public class UserRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_USERS_ROLE_SEQ_GEN")
    @Column(name = "USERS_ROLE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

}
