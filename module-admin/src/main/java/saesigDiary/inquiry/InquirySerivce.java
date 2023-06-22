package saesigDiary.inquiry;

import java.util.List;
import java.util.Map;

public interface InquirySerivce {

    public List<InquiryDto> getInquiryList(Map<String, Object> param) throws Exception;

}
