package me.lozm.domain.auth.repository;

import me.lozm.domain.auth.entity.Resource;
import me.lozm.global.code.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query("SELECT R FROM Resource R " +
            "JOIN FETCH R.roleResources " +
            "WHERE R.resourceType = :resourceType " +
            "ORDER BY R.orderNumber DESC")
    List<Resource> findAllResources(ResourceType resourceType);

}
