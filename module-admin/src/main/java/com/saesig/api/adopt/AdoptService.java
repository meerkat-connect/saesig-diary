package com.saesig.api.adopt;

import com.saesig.domain.member.Member;

import java.util.List;

public interface AdoptService {
    Long insertAdopt(AdoptRequestDto adoptRequestDto) throws Exception;
    List<AdoptListResponseDto> selectAdoptList(AdoptRequestDto adoptRequestDto) throws Exception;
    List<AdoptBreedListDto> getBreedOptions(AdoptBreedListDto adoptBreedListDto) throws Exception;
    AdoptViewResponseDto selectAdoptView(AdoptRequestDto adoptRequestDto) throws Exception;
    Long updateAdopt(AdoptRequestDto adoptRequestDto) throws Exception;
    Long deleteAdopt(AdoptRequestDto adoptRequestDto) throws Exception;
    Long changeLikeInfo(AdoptRequestDto adoptRequestDto) throws Exception;
    Long reportAdoptPost(AdoptReportDto adoptReportDto) throws Exception;
    List<AnimalVaccineListDto> getVaccineList(AnimalVaccineListDto animalVaccineListDto) throws Exception;
}
