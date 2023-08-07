package com.saesig.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;
import java.util.List;

@RequiredArgsConstructor
public class UrlResourceFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {
//    private SecurityResourceService securityResourceService;
    private final LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap;

/*    public void setSecurityResourceService(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }*/

    @Override
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
        if(resourceMap == null) {
//            resourceMap = securityResourceService.getResourceList();
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
