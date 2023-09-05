package com.saesig.inquiry;

import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @GetMapping({"/admin/inquiry/view", "/admin/inquiry/inquiryList.html"})
    public String view(Model model, @LoginMember SessionMember user) {
        String currentNickName = user.getNickname();
        model.addAttribute("loginSession", currentNickName);
        return "inquiry/inquiryList";
    }

    @RequestMapping(value = "/admin/inquiry/getInquiryList.do")
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

    @RequestMapping(value = "/admin/inquiry/getFilterData.do")
    @ResponseBody
    public Map<String, Object> getInquiryEnum() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        InquiryStatus[] inquiryStatus = InquiryStatus.values();
        Map<String,Map<String,String>> InquiryStatusMap = new HashMap<>();
        for (InquiryStatus item : inquiryStatus){
            Map<String,String> itemTitle = new HashMap<>();
            itemTitle.put("title",item.getTitle());
            InquiryStatusMap.put(item.name(),itemTitle);
        }
        InquiryCategory[] inquiryCategory = InquiryCategory.values();
        Map<String,Map<String,String>> InquiryCategoryMap = new HashMap<>();
        for (InquiryCategory item : inquiryCategory){
            Map<String,String> itemTitle = new HashMap<>();
            itemTitle.put("title",item.getTitle());
            InquiryCategoryMap.put(item.name(),itemTitle);
        }
        resultMap.put("InquiryStatus",InquiryStatusMap);
        resultMap.put("InquiryCategory",InquiryCategoryMap);
        return resultMap;
    }

    @RequestMapping(value = "/admin/inquiry/insertAnswer.do")
    @ResponseBody
    public boolean insertAnswer(@RequestBody InquiryAnswerDto param) throws Exception {
        boolean result = inquiryService.InsertAnswer(param);
        return result;
    }

    @RequestMapping(value = "/admin/inquiry/getAnswerById.do")
    @ResponseBody
    public List<InquiryAnswerDto> getAnswerById(@RequestBody Long id) throws Exception {
        return inquiryService.selectAnswerById(id);
    }
}
