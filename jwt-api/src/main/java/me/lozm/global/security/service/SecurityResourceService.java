package me.lozm.global.security.service;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.entity.AccessIp;
import me.lozm.domain.auth.entity.Resource;
import me.lozm.domain.auth.repository.AccessIpRepository;
import me.lozm.domain.auth.repository.ResourceRepository;
import me.lozm.global.code.ResourceType;
import me.lozm.global.code.UseYn;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecurityResourceService {

    private final ResourceRepository resourceRepository;
    private final AccessIpRepository accessIpRepository;


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

    //TODO Access IP 데이터 변경 시, reload 하는 기능 추가
    public List<String> getAccessIpList() {
        return accessIpRepository.findAllByUse(UseYn.USE)
                .stream()
                .map(AccessIp::getIpAddress)
                .collect(Collectors.toList());
    }

}
