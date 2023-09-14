package com.saesig.saesigManage;

import java.util.List;
import java.util.Map;

public interface SaesigManageService {

    List<AdoptListDto> selectAdoptList(AdoptListDto param) throws Exception;

    AdoptListDto selectAdoptById(Long id) throws Exception;

    public List<animalDivisionCategoryDto> selectAnimalDivision(Integer id) throws Exception;

    public List<ChattingDto> selectChattingListByAdoptId(ChattingDto cd) throws Exception;

    public List<ReportingDto> selectReportingListByAdoptId(ReportingDto rd) throws Exception;

    public AdoptListDto selectAdoptCntByAdoptId(AdoptListDto param) throws Exception;

    public Long insertOpenChatReason(ChatOpenReasonDto param) throws Exception;

    public boolean checkPassword(Long id, String password) throws Exception;

    public List<String> selectVaccineByAdoptId(Long id) throws Exception;

    public List<VaccineDto> selectVaccineList() throws Exception;
}
