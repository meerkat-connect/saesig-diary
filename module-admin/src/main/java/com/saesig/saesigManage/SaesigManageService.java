package com.saesig.saesigManage;

import java.util.List;
import java.util.Map;

public interface SaesigManageService {

    List<AdoptListDto> selectAdoptList(AdoptListDto param) throws Exception;

    AdoptListDto selectAdoptById(Long id) throws Exception;

    public List<animalDivisionCategoryDto> selectAnimalDivision(Integer id) throws Exception;
}
