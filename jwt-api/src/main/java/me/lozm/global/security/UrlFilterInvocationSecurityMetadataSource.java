package me.lozm.global.security;

import lombok.RequiredArgsConstructor;
import me.lozm.global.security.service.SecurityResourceService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequiredArgsConstructor
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final Map<RequestMatcher, List<ConfigAttribute>> requestMap;
    private final SecurityResourceService securityResourceService;


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        final HttpServletRequest request = ((FilterInvocation) object).getRequest();

        if (requestMap != null) {
            for (Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : requestMap.entrySet()) {
                final RequestMatcher matcher = entry.getKey();
                if (matcher.matches(request)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();

        for (Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public void reload() {
        requestMap.clear();

        final Map<RequestMatcher, List<ConfigAttribute>> reloadedMap = securityResourceService.getResourceList();
        final Iterator<Map.Entry<RequestMatcher, List<ConfigAttribute>>> iterator = reloadedMap.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<RequestMatcher, List<ConfigAttribute>> entry = iterator.next();
            requestMap.put(entry.getKey(), entry.getValue());
        }
    }

}
