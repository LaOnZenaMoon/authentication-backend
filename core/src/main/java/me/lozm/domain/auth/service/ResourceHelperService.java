package me.lozm.domain.auth.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.Resource;
import me.lozm.domain.auth.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ResourceHelperService {

    private final ResourceRepository resourceRepository;


    public Optional<Resource> findResource(Long resourceId) {
        return resourceRepository.findById(resourceId);
    }

    public Resource getResource(Long resourceId) {
        return findResource(resourceId)
                .orElseThrow(() -> new IllegalArgumentException(format("존재하지 않는 Resource 입니다. Resource ID: [%d]", resourceId)));
    }

    public Optional<Resource> findResource(String resourceName) {
        return resourceRepository.findByResourceName(resourceName);
    }

    public Resource getResource(String resourceName) {
        return findResource(resourceName)
                .orElseThrow(() -> new IllegalArgumentException(format("존재하지 않는 Resource 입니다. Resource 이름: [%d]", resourceName)));
    }

}
