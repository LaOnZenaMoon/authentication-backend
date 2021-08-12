package me.lozm.global.security.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.Resource;
import me.lozm.domain.auth.repository.ResourceRepository;
import me.lozm.global.code.ResourceType;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SecurityResourceService {

    private final ResourceRepository resourceRepository;


    @Transactional(readOnly = true)
    public Map<RequestMatcher, List<ConfigAttribute>> getResourceList() {
        Map<RequestMatcher, List<ConfigAttribute>> resourceMap = new LinkedHashMap<>();

        List<Resource> resourceList = resourceRepository.findAllResources(ResourceType.URL);

        resourceList.forEach(resource -> {
            List<ConfigAttribute> configAttributeList = new ArrayList<>();
            resource.getRoleResources().forEach(roleResource -> {
                configAttributeList.add(new SecurityConfig(roleResource.getRole().getRoleName()));
            });
            resourceMap.put(new AntPathRequestMatcher(resource.getResourceName()), configAttributeList);
        });

        return resourceMap;
    }

}
