package me.lozm.domain.auth.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(schema = "LOZM", name = "AUTH_ROLE_RESOURCES")
@SequenceGenerator(name = "AUTH_ROLE_RESOURCE_SEQ_GEN", sequenceName = "AUTH_ROLE_RESOURCE_SEQ", allocationSize = 1)
public class RoleResource {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_ROLE_RESOURCE_SEQ_GEN")
    @Column(name = "ROLE_RESOURCE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESOURCE_ID")
    private Resource resource;

}
