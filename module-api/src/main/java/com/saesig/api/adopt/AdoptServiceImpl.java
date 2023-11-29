package com.saesig.api.adopt;

import com.saesig.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdoptServiceImpl implements AdoptService{

    @Autowired
    private AdoptMapper adoptMapper;
    @Override
    @Transactional
    public Long insertAdopt(AdoptDto adoptDto) throws Exception{
        Long result = adoptMapper.insertAdopt(adoptDto);
        return adoptMapper.insertAdoptVaccine(adoptDto);
    };

    @Override
    public List<AdoptDto> selectAdoptList(AdoptDto adoptDto) throws Exception{
        return adoptMapper.selectAdoptList(adoptDto);
    };

    @Override
    @Transactional
    public Long updateAdopt(AdoptDto adoptDto) throws Exception{
        adoptMapper.updateAdopt(adoptDto);
        return adoptMapper.updateAdoptVaccine(adoptDto);
    };

    @Override
    public Long deleteAdopt(AdoptDto adoptDto) throws Exception{
        return adoptMapper.deleteAdopt(adoptDto);
    };

    @Override
    public Long changeLikeInfo(AdoptDto adoptDto) throws Exception{
        Long isLike = adoptMapper.selectInterestById(adoptDto);
        Long result;
        if (isLike > 0){
            result = adoptMapper.deleteInterest(adoptDto);
        }else{
            result = adoptMapper.insertInterest(adoptDto);
        }
        return result;
    };
    @Override
    public Long reportAdoptPost(AdoptReportDto adoptReportDto) throws Exception{
        return adoptMapper.reportAdoptPost(adoptReportDto);
    };
}
