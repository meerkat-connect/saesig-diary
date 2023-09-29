package com.saesig.templateManage;

import com.saesig.common.mybatis.DataTablesDto;
import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.common.Constant;
import com.saesig.global.enumCode.EnumMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/templateManage")
public class TemplateManageController {

    private final TemplateManageService templateManageService;

    private final EnumMapperFactory enumMapperFactory;


    @GetMapping({"view"})
    public String templateManageList(Model model) throws Exception {
        model.addAttribute("searchSendMethod", enumMapperFactory.get("sendMethod"));
        model.addAttribute("searchSendCategory", enumMapperFactory.get("sendCategory"));

        return "templateManage/view";
    }

    @GetMapping("selectTemplateList.do")
    @ResponseBody
    public DataTablesDto selectTemplateList(TemplateManageDto tmd) throws Exception {
        DataTablesDto dtd = new DataTablesDto();

        List<TemplateManageDto> list = templateManageService.selectTemplateList(tmd);

        dtd.setDraw(tmd.getDraw());
        dtd.setData(list);
        if(list.isEmpty()) {
            dtd.setRecordsFiltered(0);
            dtd.setRecordsTotal(0);
        }else {
            dtd.setRecordsFiltered(list.get(0).getRecordsTotal());
            dtd.setRecordsTotal(list.get(0).getRecordsTotal());
        }

        return dtd;
    }

    @GetMapping("form")
    public String templateManageForm(Long id, Model model) throws Exception {
        model.addAttribute("searchSendMethod", enumMapperFactory.get("sendMethod"));
        model.addAttribute("searchSendCategory", enumMapperFactory.get("sendCategory"));

        if (!Objects.isNull(id)){
            TemplateManageDto template = templateManageService.selectTemplate(id);
            model.addAttribute("template", template);

            return "templateManage/form";
        }else {
            TemplateManageDto template = new TemplateManageDto();
            model.addAttribute("template", template);

            return "templateManage/form";
        }
    }

    @PostMapping("insertForm.do")
    @ResponseBody
    public Map<String, Object> insertForm(@LoginMember SessionMember member, TemplateManageDto tmd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        tmd.setMember(member);
        int retVal = 0;
        retVal = templateManageService.insertForm(tmd);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }

        return resultMap;
    }

    @PostMapping("updateForm.do")
    @ResponseBody
    public Map<String, Object> updateForm(@LoginMember SessionMember member, TemplateManageDto tmd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        tmd.setMember(member);
        int retVal = 0;
        retVal = templateManageService.updateForm(tmd);
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }

        return resultMap;
    }

    @DeleteMapping("deleteItems.do")
    @ResponseBody
    public Map<String, Object> deleteItems(@LoginMember SessionMember member, @RequestParam Long[] ids) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        int retVal = 0;
        retVal = templateManageService.deleteItems(ids);
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패했습니다.");
        }

        return resultMap;
    }
}
