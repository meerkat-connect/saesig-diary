package com.saesig.saesigManage;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface SaesigManageMapper {

    public List<AdoptListDto> selectAdoptList(AdoptListDto param);

    public AdoptListDto selectAdoptById(Long id);

    public List<animalDivisionCategoryDto> selectAnimalDivision1List();

    public List<animalDivisionCategoryDto> selectAnimalDivision2List(Integer id);

    public List<ChattingDto> selectChattingListByAdoptId(ChattingDto cd);

    public List<ReportingDto> selectReportingListByAdoptId(ReportingDto rd);

    public AdoptListDto selectAdoptCntByAdoptId(AdoptListDto param);

    public Long insertOpenChatReason(ChatOpenReasonDto param);

    public Map<String, Object>checkPassword(@Param("id") Long id,@Param("password") String password);

    public List<Integer> selectVaccineByAdoptId(Long id);

    public List<VaccineDto> selectVaccineList();

    public Long updateAdoptInfo(AdoptListDto param);
    public Long insertAdoptStatusChangeLog(AdoptListDto param);

    public Long updateAdoptVaccine(AdoptListDto param);

    public List<AdoptHistoryDto> selectHistoryByAdoptId(AdoptHistoryDto param);

}
