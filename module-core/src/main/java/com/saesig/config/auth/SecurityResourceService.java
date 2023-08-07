package com.saesig.config.auth;

import com.saesig.domain.role.Resource;
import com.saesig.domain.role.ResourceRepository;
import com.saesig.domain.role.RoleResource;
import com.saesig.domain.role.RoleResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SecurityResourceService {
    private final ResourceRepository resourceRepository;
    private final RoleResourceRepository roleResourceRepository;

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();

        List<Resource> resources = resourceRepository.findAll();
        resources.forEach(resource -> {
            List<ConfigAttribute> configAttributes = new ArrayList<>();

            Long resourceId = resource.getId();
            List<RoleResource> roleResources = roleResourceRepository.findAllByResourceId(resourceId);

            for (RoleResource roleResource : roleResources) {
                configAttributes.add(new SecurityConfig(roleResource.getRole().getName()));
            }

            result.put(new AntPathRequestMatcher(resource.getUrl()), configAttributes);
        });

        return result;
    }
}
