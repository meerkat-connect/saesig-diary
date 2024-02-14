package com.saesig.api.adopt;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdoptMapper {
    public Long insertAdopt(AdoptDto adoptDto) throws Exception;

    public Long insertAdoptVaccine(AdoptDto adoptDto) throws Exception;

    public List<AdoptDto> selectAdoptList(AdoptDto adoptDto) throws Exception;
    public Long updateAdopt(AdoptDto adoptDto) throws Exception;
    public Long updateAdoptVaccine(AdoptDto adoptDto) throws Exception;
    public Long deleteAdopt(AdoptDto adoptDto) throws Exception;
    public Long reportAdoptPost(AdoptReportDto adoptReportDto) throws Exception;
    public Long selectInterestById(AdoptDto adoptDto) throws Exception;
    public Long insertInterest(AdoptDto adoptDto) throws Exception;
    public Long deleteInterest(AdoptDto adoptDto) throws Exception;

}
