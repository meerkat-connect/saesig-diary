package com.saesig.config.auth;

import com.saesig.domain.role.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class UrlBasedFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private final SecurityResourceService securityResourceService;

    private final CacheManager cacheManager;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceList = getResourceListFromCache();

        for (Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : resourceList.entrySet()) {
            RequestMatcher matcher = entry.getKey();
            if (matcher.matches(request)) {
                return entry.getValue();
            }
        }

        return null;
    }

    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceListFromCache() {
        Cache cache = cacheManager.getCache("resourceList");
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceList = cache.get("allResources", LinkedHashMap.class);
        if(resourceList == null) {
            log.info("역할 관련 리소스 캐시 조회");
            resourceList = securityResourceService.getResourceList();
        }
        return resourceList;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceList = getResourceListFromCache();

        return resourceList.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
