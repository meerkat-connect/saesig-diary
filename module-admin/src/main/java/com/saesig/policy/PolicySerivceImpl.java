package com.saesig.policy;


import com.saesig.policy.PolicyDto;
import com.saesig.policy.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicySerivceImpl implements PolicyService {

    private final PolicyMapper policyMapper;
    @Override
    public PolicyDto getPolicyByType(String Type) {
        return policyMapper.getPolicyByType(Type);
    };

    @Override
    public boolean ChangePolicy(PolicyDto param) throws Exception {
        policyMapper.updatePolicy(param);
        return true;
    }




}
