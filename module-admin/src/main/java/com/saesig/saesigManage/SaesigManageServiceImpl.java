package com.saesig.saesigManage;

import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SaesigManageServiceImpl implements SaesigManageService{
    private final SaesigManageMapper saesigManageMapper;

    @Override
    public List<AdoptListDto> selectAdoptList(AdoptListDto param) throws Exception {
        return saesigManageMapper.selectAdoptList(param);
    }

    @Override
    public AdoptListDto selectAdoptById(Long id) throws Exception {
        return saesigManageMapper.selectAdoptById(id);
    }

    @Override
    public List<animalDivisionCategoryDto> selectAnimalDivision(Integer id) throws Exception {
        if (id != null){
            return saesigManageMapper.selectAnimalDivision2List(id);
        }else{
            return saesigManageMapper.selectAnimalDivision1List();
        }

    };

    @Override
    public List<ChattingDto> selectChattingListByAdoptId(ChattingDto cd) throws Exception {
        return saesigManageMapper.selectChattingListByAdoptId(cd);
    };

    @Override
    public List<ReportingDto> selectReportingListByAdoptId(ReportingDto rd) throws Exception {
        return saesigManageMapper.selectReportingListByAdoptId(rd);
    };

    @Override
    public AdoptListDto selectAdoptCntByAdoptId(AdoptListDto param) throws Exception {
        return saesigManageMapper.selectAdoptCntByAdoptId(param);
    };

    @Override
    public Long insertOpenChatReason(ChatOpenReasonDto param) throws Exception{
        return saesigManageMapper.insertOpenChatReason(param);
    };

    @Override
    public boolean checkPassword(Long id, String password) throws Exception{

        Map<String, Object> result = saesigManageMapper.checkPassword(id, password);
        if (result.size() > 0){
            return true;
        }else{
            return false;
        }
    };

    @Override
    public List<Integer> selectVaccineByAdoptId(Long id) throws Exception{
     return saesigManageMapper.selectVaccineByAdoptId(id);
    }

    @Override
    public List<VaccineDto> selectVaccineList() throws Exception{
        return saesigManageMapper.selectVaccineList();
    };

    @Override
    public Long updateAdoptInfo(AdoptListDto param, SessionMember member) throws Exception{
        return saesigManageMapper.updateAdoptInfo(param,member.getId());
    }

   @Override
   public Long insertAdoptStatusChangeLog(AdoptListDto param, SessionMember member) throws Exception{
        return saesigManageMapper.insertAdoptStatusChangeLog(param, member.getId());
   }
}
