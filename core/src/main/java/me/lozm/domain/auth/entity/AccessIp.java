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
@Table(schema = "LOZM", name = "AUTH_ACCESS_IP")
@SequenceGenerator(name = "AUTH_ACCESS_IP_SEQ_GEN", sequenceName = "AUTH_ACCESS_IP_SEQ", allocationSize = 1)
public class AccessIp extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_ACCESS_IP_SEQ_GEN")
    @Column(name = "ACCESS_IP_ID")
    private Long id;

    @Column(name = "ROLE_NAME", nullable = false)
    private String ipAddress;

}
