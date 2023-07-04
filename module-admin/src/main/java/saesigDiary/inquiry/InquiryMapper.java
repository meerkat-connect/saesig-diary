package saesigDiary.inquiry;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InquiryMapper {

    public List<InquiryDto> getInquiryList(Map<String, Object> param);

    public void insertAnswer(InquiryAnswerDto param);

    public void updateInquiryAnswerStatus(InquiryAnswerDto param);

    public List<InquiryAnswerDto> selectAnswerById(Long id);
}
