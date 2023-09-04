package com.saesig.saesigManage;

import org.apache.ibatis.annotations.Mapper;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@Mapper
public interface SaesigManageMapper {

    public List<AdoptListDto> selectAdoptList(AdoptListDto param);

    public AdoptListDto selectAdoptById(Long id);

    public List<animalDivisionCategoryDto> selectAnimalDivision1List();

    public List<animalDivisionCategoryDto> selectAnimalDivision2List(Integer id);

}
