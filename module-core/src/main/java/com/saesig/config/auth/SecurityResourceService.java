package com.saesig.config.auth;

import com.saesig.domain.role.Resource;
import com.saesig.domain.role.ResourceRepository;
import com.saesig.domain.role.RoleResource;
import com.saesig.domain.role.RoleResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SecurityResourceService {
    private final ResourceRepository resourceRepository;
    private final RoleResourceRepository roleResourceRepository;

    @Cacheable(cacheNames = "resourceList", key="'allResources'")
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();

        List<Resource> resources = resourceRepository
                .findAll()
                .stream()
                .filter(resource -> StringUtils.hasText(resource.getUrl()))
                .collect(Collectors.toList());

        resources.forEach(resource -> {
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            Set<String> duplicateCheck = new HashSet<>();

            Long resourceId = resource.getId();
            List<RoleResource> roleResources = roleResourceRepository.findAllByResourceId(resourceId);

            for (RoleResource roleResource : roleResources) {
                if(!duplicateCheck.contains(roleResource.getRole().getName())) {
                    configAttributes.add(new SecurityConfig(roleResource.getRole().getName()));
                    duplicateCheck.add(roleResource.getRole().getName());
                }
            }

            if("Y".equals(resource.getIsLoginDisallowed())){
                configAttributes.add(new SecurityConfig("ROLE_ANONYMOUS"));
            }

            result.put(new AntPathRequestMatcher(resource.getUrl(), resource.getHttpMethod()), configAttributes);
        });

        return result;
    }
}
