package com.saesig.inquiry;

import java.util.List;
import java.util.Map;

public interface InquiryService {

    public List<InquiryDto> getInquiryList(InquiryDto param) throws Exception;

    public boolean InsertAnswer(InquiryAnswerDto param) throws Exception;

    public List<InquiryAnswerDto> selectAnswerById(Long id) throws Exception;

    public Long deleteInquiry(Long[] ids) throws Exception;

}
