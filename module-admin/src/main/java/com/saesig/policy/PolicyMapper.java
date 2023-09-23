package com.saesig.policy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PolicyMapper {
    public PolicyDto getPolicyByType(@Param("type") String type);

    public void updatePolicy(PolicyDto param);
}