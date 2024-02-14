package com.saesig.api.adopt;

import java.util.List;

public interface AdoptService {
    Long insertAdopt(AdoptDto adoptDto) throws Exception;
    List<AdoptDto> selectAdoptList(AdoptDto adoptDto) throws Exception;
    Long updateAdopt(AdoptDto adoptDto) throws Exception;
    Long deleteAdopt(AdoptDto adoptDto) throws Exception;
    Long changeLikeInfo(AdoptDto adoptDto) throws Exception;
    Long reportAdoptPost(AdoptReportDto adoptReportDto) throws Exception;
}
