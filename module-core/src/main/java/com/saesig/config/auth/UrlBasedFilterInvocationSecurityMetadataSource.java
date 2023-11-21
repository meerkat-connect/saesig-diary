package com.saesig.config.auth;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

public class UrlBasedFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private final SecurityResourceService securityResourceService;
    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap = new LinkedHashMap<>();


    public UrlBasedFilterInvocationSecurityMetadataSource(LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap, SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
        this.requestMap = requestMap;
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        if (requestMap != null) {
            for (Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : requestMap.entrySet()) {
                RequestMatcher matcher = entry.getKey();
                if (matcher.matches(request)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return requestMap.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public void reload() {
        requestMap.clear();

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resources = securityResourceService.getResourceList();
        Iterator<Map.Entry<RequestMatcher, List<ConfigAttribute>>> iterator = resources.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<RequestMatcher, List<ConfigAttribute>> next = iterator.next();
            requestMap.put(next.getKey(), next.getValue());
        }
    }
}
