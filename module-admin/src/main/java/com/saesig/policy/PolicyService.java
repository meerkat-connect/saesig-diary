package com.saesig.policy;

public interface PolicyService {

    public PolicyDto getPolicyByType(String Type);

    public boolean ChangePolicy(PolicyDto param);

}