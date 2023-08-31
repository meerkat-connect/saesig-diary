package com.saesig.saesigManage;

import com.saesig.faq.FaqRequestDto;
import com.saesig.faq.FaqResponseDto;
import com.saesig.faq.FaqService;
import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/saesigManage")
public class SaesigManageController {
    private final SaesigManageService saesigManageService;
    private final EnumMapperFactory enumFactory;


    @GetMapping("/view")
    public String faqView(Model model) {
        model.addAttribute("adoptStatus", enumFactory.get("adoptStatus"));
        return "saesigManage/view";
    }

    @GetMapping("/selectAdoptList.do")
    @ResponseBody
    public Map<String,Object> selectAdoptList(@RequestParam Map<String, Object> param) throws Exception {
        List<AdoptListDto> result = saesigManageService.selectAdoptList(param);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",result);
        return resultMap;
    }

    @GetMapping({"/{id}/infoForm", "/form"})
    public String findByIdView(@PathVariable(required = false) Optional<Long> id, Model model) throws Exception {
        if (id.isPresent()) {
            AdoptListDto byId = saesigManageService.selectAdoptById(id.get());
            model.addAttribute("adopt", byId);
        } else {
            AdoptListDto byId = new AdoptListDto();
            model.addAttribute("adopt", byId);
        }

        model.addAttribute("adoptStatus", enumFactory.get("adoptStatus"));
        return "saesigManage/infoForm";
    }


    @RequestMapping(value = "/admin/news/getFilterData.do")
    @ResponseBody
    public Map<String, Object> getNewsEnum() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        Map<String,Map<String,String>> NewsStatusMap = new HashMap<>();
        Map<String,Map<String,String>> NewsCategoryMap = new HashMap<>();
        resultMap.put("NewsStatus",NewsStatusMap);
        resultMap.put("NewsCategory",NewsCategoryMap);
        return resultMap;
    }


}
