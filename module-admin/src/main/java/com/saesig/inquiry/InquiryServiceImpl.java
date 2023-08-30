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
    public List<InquiryDto> getInquiryList(Map<String, Object> param) throws Exception {
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
}