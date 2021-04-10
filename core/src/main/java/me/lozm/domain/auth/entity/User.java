package me.lozm.domain.auth.entity;

import lombok.*;
import me.lozm.global.code.UsersType;
import me.lozm.global.code.converter.UseYnConverter;
import me.lozm.global.common.BaseEntity;

import javax.persistence.*;


@Entity
@Table(schema = "LOZM", name = "USERS")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 50)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IDENTIFIER")
    private String identifier;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TYPE")
    @Convert(converter = UseYnConverter.class)
    private UsersType type;

}
