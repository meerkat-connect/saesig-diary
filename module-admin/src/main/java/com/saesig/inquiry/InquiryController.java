package com.saesig.inquiry;

import com.saesig.common.mybatis.DataTablesDto;
import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import com.saesig.global.enumCode.EnumMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    private final EnumMapperFactory enumFactory;

    @GetMapping({"/view", "/inquiryList.html"})
    public String view(Model model, @LoginMember SessionMember user) {
        model.addAttribute("loginSession", user);
        model.addAttribute("categoryList", enumFactory.get("inquiryCategory"));
        model.addAttribute("statusList", enumFactory.get("inquiryStatus"));
        return "inquiry/inquiryList";
    }

    @RequestMapping(value = "/getInquiryList.do")
    @ResponseBody
    public DataTablesDto getInquiryList(InquiryDto param) throws Exception {
        DataTablesDto dtd = new DataTablesDto();
        List<InquiryDto> list = inquiryService.getInquiryList(param);
        dtd.setData(list);
        dtd.setDraw(param.getDraw());
        if (list.size() > 0){
            dtd.setRecordsFiltered(list.get(0).getRecordsTotal());
            dtd.setRecordsTotal(list.get(0).getRecordsTotal());
        }else{
            dtd.setRecordsFiltered(0);
            dtd.setRecordsTotal(0);
        }
        return dtd;
    }

    @GetMapping(value = "/getInquiryById.do")
    public String getInquiryById(InquiryDto param, Model model) throws Exception {
        model.addAttribute("inquiry", inquiryService.selectInquiryById(param.getId()));
        return "inquiry/inquiryForm";
    }

    @RequestMapping(value = "/insertAnswer.do")
    @ResponseBody
    public boolean insertAnswer(@RequestBody InquiryAnswerDto param) throws Exception {
        boolean result = inquiryService.InsertAnswer(param);
        return result;
    }

    @RequestMapping(value = "/getAnswerById.do")
    @ResponseBody
    public List<InquiryAnswerDto> getAnswerById(@RequestBody Long id) throws Exception {
        return inquiryService.selectAnswerById(id);
    }

    @DeleteMapping(value = "/deleteInquiry.do")
    @ResponseBody
    public Long deleteInquiry(@RequestBody Long[] ids) throws Exception{
        return inquiryService.deleteInquiry(ids);
    }

}
