package saesigDiary.inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class InquiryController {

    @Autowired
    private InquirySerivce inquiryService;

    @GetMapping({"/inquiry", "/inquiry/inquiryList.html"})
    public String example() {
        return "inquiry/inquiryList";
    }

    @RequestMapping(value = "/inquiry/getInquiryList.do")
    @ResponseBody
    public Map<String,Object> getInquiryList(@RequestParam Map<String, Object> param) throws Exception {
        List<InquiryDto> result = inquiryService.getInquiryList(param);
        for (int i=0;i<result.size();i++){
            result.get(i).setCategoryStr(result.get(i).getCategory().getTitle());
            result.get(i).setStatusStr(result.get(i).getStatus().getTitle());
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",result);
        return resultMap;
    }

    @RequestMapping(value = "/inquiry/getFilterData.do")
    @ResponseBody
    public Map<String, Object> getInquiryEnum() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        InquiryStatus[] inquiryStatus = InquiryStatus.values();

        Map<String,Map<String,String>> inquiryStatusMap = new HashMap<>();
        for (InquiryStatus item : inquiryStatus){
            Map<String,String> itemTitle = new HashMap<>();
            itemTitle.put("title",item.getTitle());
            inquiryStatusMap.put(item.name(),itemTitle);
        }
        InquiryCategory[] inquiryCategory = InquiryCategory.values();
        Map<String,Map<String,String>> InquiryCategoryMap = new HashMap<>();
        for (InquiryCategory item : inquiryCategory){
            Map<String,String> itemTitle = new HashMap<>();
            itemTitle.put("title",item.getTitle());
            InquiryCategoryMap.put(item.name(),itemTitle);
        }
        resultMap.put("InquiryStatus",inquiryStatusMap);
        resultMap.put("InquiryCategory",InquiryCategoryMap);
        return resultMap;
    }


}
