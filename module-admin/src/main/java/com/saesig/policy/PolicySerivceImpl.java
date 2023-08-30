package com.saesig.policy;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicySerivceImpl implements PolicyService {

    private final PolicyMapper policyMapper;
    @Override
    public PolicyDto getPolicyByType(String Type) {
        return policyMapper.getPolicyByType(Type);
    };

    @Override
    public boolean ChangePolicy(PolicyDto param) {
        policyMapper.updatePolicy(param);
        return true;
    }




}
