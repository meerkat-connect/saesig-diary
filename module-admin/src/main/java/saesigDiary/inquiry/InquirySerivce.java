package saesigDiary.inquiry;

import java.util.List;
import java.util.Map;

public interface InquirySerivce {

    public List<InquiryDto> getInquiryList(Map<String, Object> param) throws Exception;

    public boolean InsertAnswer(InquiryAnswerDto param) throws Exception;

    public List<InquiryAnswerDto> selectAnswerById(Long id) throws Exception;

}
