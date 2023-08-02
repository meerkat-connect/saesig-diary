package com.saesig.templateManage;

import com.saesig.global.enumCode.EnumMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

@RequiredArgsConstructor
@Controller
public class TemplateManageController {

    private final TemplateManageService templateManageService;

    private final EnumMapperFactory enumMapperFactory;


    @GetMapping({"/templateManage","/templateManage/templateManageList.html"})
    public String templateManageList(TemplateManageDto tmd, Model model) throws Exception {
        model.addAttribute("searchSendMethod", enumMapperFactory.get("sendMethod"));
        model.addAttribute("searchSendCategory", enumMapperFactory.get("sendCategory"));

        return "/templateManage/templateManageList";
    }

    @GetMapping("/templateManage/selectTemplateList.do")
    @ResponseBody
    public Map<String, Object> selectTemplateList(TemplateManageDto tmd) throws Exception {
        // TODO: 2023-06-23 : need paging
        Map<String, Object> result = new HashMap<>();

        List<TemplateManageDto> list = templateManageService.selectTemplateList(tmd);

//        if (list.size() > 0) {
//            result.put("totalCount", (list.get(0).getTotalCount()));
//        } else {
//            result.put("totalCount", 0);
//        }

        result.put("data", list);

        return result;
    }

    @GetMapping("/templateManage/templateManageForm.html")
    public String templateManageForm(Long id, Model model) throws Exception {
        model.addAttribute("searchSendMethod", enumMapperFactory.get("sendMethod"));
        model.addAttribute("searchSendCategory", enumMapperFactory.get("sendCategory"));

        if (!Objects.isNull(id)){
            TemplateManageDto template = templateManageService.selectTemplate(id);
            model.addAttribute("template", template);

            return "/templateManage/templateManageForm";
        }else {
            TemplateManageDto template = new TemplateManageDto();
            model.addAttribute("template", template);

            return "/templateManage/templateManageForm";
        }
    }

    @PostMapping("/templateManage/insertTemplate.do")
    @ResponseBody
    public Map<String, Object> insertTemplate(TemplateManageDto tmd) throws Exception {
        // TODO: 2023-06-23 : need validation check, need user object
        Map<String, Object> resultMap = new HashMap<>();

        int retVal = 0;

//        retVal = templateManageService.insertTemplate(tmd);

        if (retVal > 0) {
//            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
//            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }
}
