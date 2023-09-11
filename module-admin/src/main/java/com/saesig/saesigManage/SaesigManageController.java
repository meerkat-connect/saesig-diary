package com.saesig.saesigManage;

import com.saesig.common.mybatis.DataTablesDto;
import com.saesig.global.enumCode.EnumMapperFactory;
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
    public String AdoptView(Model model) throws Exception {
        model.addAttribute("adoptStatus", enumFactory.get("adoptStatus"));
        model.addAttribute("animal_division1", saesigManageService.selectAnimalDivision(null));
        return "saesigManage/view";
    }

    @GetMapping("/selectAdoptList.do")
    @ResponseBody
    public DataTablesDto selectAdoptList(AdoptListDto ald) throws Exception {
        DataTablesDto dtd = new DataTablesDto();
        List<AdoptListDto> list = saesigManageService.selectAdoptList(ald);
        dtd.setData(list);
        dtd.setDraw(ald.getDraw());
        if (list.size() > 0){
            dtd.setRecordsFiltered(list.get(0).getRecordsTotal());
            dtd.setRecordsTotal(list.get(0).getRecordsTotal());
        }else{
            dtd.setRecordsFiltered(0);
            dtd.setRecordsTotal(0);
        }
        return dtd;
    }

    @GetMapping({"/{id}/infoForm", "/form"})
    public String infoForm(@PathVariable(required = false) Optional<Long> id, Model model) throws Exception {
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

    @GetMapping({"/{id}/chattingForm"})
    public String chattingForm(@PathVariable(required = false) Optional<Long> id, Model model) throws Exception {
        if (id.isPresent()) {
            AdoptListDto byId = saesigManageService.selectAdoptById(id.get());
            model.addAttribute("adopt", byId);
        } else {
            AdoptListDto byId = new AdoptListDto();
            model.addAttribute("adopt", byId);
        }
        model.addAttribute("adoptStatus", enumFactory.get("adoptStatus"));
        return "saesigManage/chattingForm";
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

    @GetMapping("/{id}/getAnimalDivision2List.do")
    public String getAnimalDivision2List(@PathVariable(required = false) Integer id, Model model) throws Exception {
        List<animalDivisionCategoryDto> animalDivision2List = saesigManageService.selectAnimalDivision(id);
        model.addAttribute("animal_division2", animalDivision2List);
        return "saesigManage/view :: #searchAnimalDivision2Category";
    }

    @GetMapping("/{id}/selectChattingListByAdoptId")
    public DataTablesDto selectChattingListByAdoptId(ChattingDto cd) throws Exception {
        DataTablesDto dtd = new DataTablesDto();
        List<ChattingDto> list = saesigManageService.selectChattingListByAdoptId(cd);
        dtd.setData(list);
        dtd.setDraw(cd.getDraw());
        if (list.size() > 0){
            dtd.setRecordsFiltered(list.get(0).getRecordsTotal());
            dtd.setRecordsTotal(list.get(0).getRecordsTotal());
        }else{
            dtd.setRecordsFiltered(0);
            dtd.setRecordsTotal(0);
        }
        return dtd;
    }

    @GetMapping("/{id}/selectReportingListByAdoptId")
    public DataTablesDto selectReportingListByAdoptId(ReportingDto rd) throws Exception {
        DataTablesDto dtd = new DataTablesDto();
        List<ReportingDto> list = saesigManageService.selectReportingListByAdoptId(rd);
        dtd.setData(list);
        dtd.setDraw(rd.getDraw());
        if (list.size() > 0){
            dtd.setRecordsFiltered(list.get(0).getRecordsTotal());
            dtd.setRecordsTotal(list.get(0).getRecordsTotal());
        }else{
            dtd.setRecordsFiltered(0);
            dtd.setRecordsTotal(0);
        }
        return dtd;
    }

}
