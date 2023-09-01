package com.saesig.saesigManage;

import java.util.List;
import java.util.Map;

public interface SaesigManageService {

    List<AdoptListDto> selectAdoptList(Map<String, Object> param) throws Exception;

    AdoptListDto selectAdoptById(Long id) throws Exception;


}
