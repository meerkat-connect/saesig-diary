package com.saesig.inquiry;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryMapper inquiryMapper;

    @Override
    public List<InquiryDto> getInquiryList(InquiryDto param) throws Exception {
        return inquiryMapper.getInquiryList(param);
    }

    @Override
    public boolean InsertAnswer(InquiryAnswerDto param) throws Exception {
        inquiryMapper.insertAnswer(param);
        inquiryMapper.updateInquiryAnswerStatus(param);
        return true;
    }

    @Override
    public List<InquiryAnswerDto> selectAnswerById(Long id) throws Exception {
        return inquiryMapper.selectAnswerById(id);
    }
    @Override
    public Long deleteInquiry(Long[] ids) throws Exception{
        return inquiryMapper.deleteInquiry(ids);
    };

    @Override
    public InquiryDto selectInquiryById(Long id) throws Exception{
        return inquiryMapper.selectInquiryById(id);
    };
}