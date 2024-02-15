package com.saesig.api.adopt;

import com.saesig.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdoptMapper {
    public Long insertAdopt(AdoptRequestDto adoptRequestDto) throws Exception;
    public Long insertAdoptVaccine(AdoptRequestDto adoptRequestDto) throws Exception;
    public List<AdoptListResponseDto> selectAdoptList(AdoptRequestDto adoptRequestDto) throws Exception;
    public Long updateAdopt(AdoptRequestDto adoptRequestDto) throws Exception;
    public Long updateAdoptVaccine(AdoptRequestDto adoptRequestDto) throws Exception;
    public Long deleteAdopt(AdoptRequestDto adoptRequestDto) throws Exception;
    public Long reportAdoptPost(AdoptReportDto adoptReportDto) throws Exception;
    public Long selectInterestById(AdoptRequestDto adoptRequestDto) throws Exception;
    public Long insertInterest(AdoptRequestDto adoptRequestDto) throws Exception;
    public Long deleteInterest(AdoptRequestDto adoptRequestDto) throws Exception;
    public List<AdoptBreedListDto> getBreedOptions(AdoptBreedListDto adoptBreedListDto) throws Exception;
    public List<AnimalVaccineListDto> getVaccineList(AnimalVaccineListDto animalVaccineListDto) throws Exception;
    public AdoptViewResponseDto selectAdoptView(AdoptRequestDto adoptRequestDto) throws Exception;

}
