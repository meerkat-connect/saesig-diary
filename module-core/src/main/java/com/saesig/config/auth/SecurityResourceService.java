package com.saesig.config.auth;

import com.saesig.domain.role.ResourceRepository;
import com.saesig.domain.role.RoleResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SecurityResourceService {
    private final ResourceRepository resourceRepository;
    private final RoleResourceRepository roleResourceRepository;

    public Map<RequestMatcher, List<ConfigAttribute>> getResourceList() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resultMap = new LinkedHashMap<>();

        /*LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resourcesList = resourcesRepository.findAllResources();
        resourcesList.forEach(re ->{
            List<ConfigAttribute> configAttributeList =  new ArrayList<>();
            re.getRoleSet().forEach(role -> {
                configAttributeList.add(new SecurityConfig(role.getRoleName()));
            });
            result.put(new AntPathRequestMatcher(re.getResourceName()),configAttributeList);

        });
        return result;*/
        return resultMap;
    }
}
