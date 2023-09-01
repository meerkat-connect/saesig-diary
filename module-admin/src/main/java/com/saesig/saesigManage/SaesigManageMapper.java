package com.saesig.saesigManage;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaesigManageMapper {

    public List<AdoptListDto> selectAdoptList(Map<String, Object> param);

    public AdoptListDto selectAdoptById(Long id);

}
