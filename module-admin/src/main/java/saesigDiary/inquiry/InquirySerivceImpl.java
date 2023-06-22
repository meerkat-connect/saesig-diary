package saesigDiary.inquiry;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InquirySerivceImpl implements InquirySerivce {

    private final InquiryMapper inquiryMapper;

    public List<InquiryDto> getInquiryList(Map<String, Object> param) throws Exception {
        return inquiryMapper.getInquiryList(param);
    }

}
