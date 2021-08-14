package me.lozm.global.security.factory;

import lombok.RequiredArgsConstructor;
import me.lozm.global.security.service.SecurityResourceService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class UrlResourceMapFactoryBean implements FactoryBean<Map<RequestMatcher, List<ConfigAttribute>>> {

    private Map<RequestMatcher, List<ConfigAttribute>> resourceMap;


    private final SecurityResourceService securityResourceService;


    @Override
    public Map<RequestMatcher, List<ConfigAttribute>> getObject() {
        if (resourceMap == null) {
            resourceMap = securityResourceService.getResourceList();
        }

        return resourceMap;
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
