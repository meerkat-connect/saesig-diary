package com.saesig.policy;

import java.util.List;
import java.util.Map;

public interface PolicyService {

    public PolicyDto getPolicyByType(String Type);

    public boolean ChangePolicy(PolicyDto param) throws Exception;

}