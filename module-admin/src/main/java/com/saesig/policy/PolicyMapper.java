package com.saesig.policy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PolicyMapper {
    public PolicyDto getPolicyByType(String type);

    public void updatePolicy(PolicyDto param);
}