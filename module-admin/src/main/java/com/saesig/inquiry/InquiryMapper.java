package com.saesig.inquiry;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface InquiryMapper {

    public List<InquiryDto> getInquiryList(InquiryDto param);

    public void insertAnswer(InquiryAnswerDto param);

    public void updateInquiryAnswerStatus(InquiryAnswerDto param);

    public List<InquiryAnswerDto> selectAnswerById(Long id);

    public Long deleteInquiry(@Param("ids") Long[] ids);

    public InquiryDto selectInquiryById(@Param("id") Long id);
}
