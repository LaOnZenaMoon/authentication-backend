package me.lozm.domain.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.Resource;
import me.lozm.domain.auth.entity.Role;
import me.lozm.domain.auth.repository.ResourceRepository;
import me.lozm.domain.auth.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceHelperService {

    private final ResourceRepository resourceRepository;


    public Optional<Resource> findResource(Long resourceId) {
        return resourceRepository.findById(resourceId);
    }

    public Resource getResource(Long resourceId) {
        return findResource(resourceId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("존재하지 않는 Resource 입니다. Resource ID: [%d]", resourceId)));
    }

}
