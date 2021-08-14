package me.lozm.domain.auth.repository;

import me.lozm.domain.auth.entity.AccessIp;
import me.lozm.global.code.UseYn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {

    Optional<AccessIp> findByIpAddressAndUse(String ipAddress, UseYn useYn);

    List<AccessIp> findAllByUse(UseYn useYn);

}
