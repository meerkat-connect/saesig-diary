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
    public Long insertAdopt(AdoptRequestDto adoptRequestDto) throws Exception{
        Long result = adoptMapper.insertAdopt(adoptRequestDto);
        return adoptMapper.insertAdoptVaccine(adoptRequestDto);
    };

    @Override
    public List<AdoptListResponseDto> selectAdoptList(AdoptRequestDto adoptRequestDto) throws Exception{
        return adoptMapper.selectAdoptList(adoptRequestDto);
    };

    @Override
    @Transactional
    public Long updateAdopt(AdoptRequestDto adoptRequestDto) throws Exception{
        adoptMapper.updateAdopt(adoptRequestDto);
        return adoptMapper.updateAdoptVaccine(adoptRequestDto);
    };

    @Override
    public Long deleteAdopt(AdoptRequestDto adoptRequestDto) throws Exception{
        return adoptMapper.deleteAdopt(adoptRequestDto);
    };

    @Override
    public Long changeLikeInfo(AdoptRequestDto adoptRequestDto) throws Exception{
        Long isLike = adoptMapper.selectInterestById(adoptRequestDto);
        Long result;
        if (isLike > 0){
            result = adoptMapper.deleteInterest(adoptRequestDto);
        }else{
            result = adoptMapper.insertInterest(adoptRequestDto);
        }
        return result;
    };
    @Override
    public Long reportAdoptPost(AdoptReportDto adoptReportDto) throws Exception{
        return adoptMapper.reportAdoptPost(adoptReportDto);
    };

    @Override
    public List<AdoptBreedListDto> getBreedOptions(AdoptBreedListDto adoptBreedListDto) throws Exception{
        return adoptMapper.getBreedOptions(adoptBreedListDto);
    }
    @Override
    public List<AnimalVaccineListDto> getVaccineList(AnimalVaccineListDto animalVaccineListDto) throws Exception{
        return adoptMapper.getVaccineList(animalVaccineListDto);
    };

    @Override
    public AdoptViewResponseDto selectAdoptView(AdoptRequestDto adoptRequestDto) throws Exception{
        return adoptMapper.selectAdoptView(adoptRequestDto);
    };
}
