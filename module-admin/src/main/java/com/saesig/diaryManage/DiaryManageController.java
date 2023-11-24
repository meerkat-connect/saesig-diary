package com.saesig.diaryManage;


import com.querydsl.apt.VisitorConfig;
import com.saesig.common.mybatis.DataTablesDto;
import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.adopt.AdoptStopCategory;
import com.saesig.domain.diary.DiaryCategory;
import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.global.enumCode.EnumMapperValue;
import com.saesig.saesigManage.AdoptListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/diaryManage")
public class DiaryManageController {
    private final DiaryManageService diaryManageService;
    private final EnumMapperFactory enumFactory;
    @GetMapping("view")
    public String DiaryView(Model model) throws Exception{
        model.addAttribute("diaryCategory", enumFactory.get("diaryCategory"));
        model.addAttribute("diaryStatus", enumFactory.get("diaryStatus"));
        return "diaryManage/view";
    }

    @GetMapping("/selectDiaryList.do")
    @ResponseBody
    public DataTablesDto selectAdoptList(DiaryManageDto dmd) throws Exception {
        DataTablesDto dtd = new DataTablesDto();
        List<DiaryManageDto> list = diaryManageService.selectDiaryList(dmd);
        dtd.setData(list);
        dtd.setDraw(dmd.getDraw());
        if (list.size() > 0){
            dtd.setRecordsFiltered(list.get(0).getRecordsTotal());
            dtd.setRecordsTotal(list.get(0).getRecordsTotal());
        }else{
            dtd.setRecordsFiltered(0);
            dtd.setRecordsTotal(0);
        }
        return dtd;
    }

    @GetMapping({"/detailForm.html"})
    public String detailForm(DiaryManageDto diaryManageDto, Model model) throws Exception {
        Long id = diaryManageDto.getId();
        if (id != 0) {
            DiaryManageDto byId = diaryManageService.selectDiaryCntData(diaryManageDto);
            model.addAttribute("diary", byId);
        } else {
            AdoptListDto byId = new AdoptListDto();
            model.addAttribute("diary", byId);
        }
        return "diaryManage/detailForm.html";
    }

    @GetMapping({"/infoForm.html"})
    public String infoForm(DiaryManageDto diaryManageDto, Model model) throws Exception {
        Long id = diaryManageDto.getId();
        model.addAttribute("weatherCategory", enumFactory.get("weatherCategory"));
        model.addAttribute("diaryCategory", enumFactory.get("diaryCategory"));
        model.addAttribute("diaryStates", enumFactory.get("diaryStatus"));
        if (id != 0) {
            DiaryManageDto byId = diaryManageService.selectDiaryById(diaryManageDto);
            model.addAttribute("diary", byId);
        } else {
            DiaryManageDto byId = new DiaryManageDto();
            model.addAttribute("diary", byId);
        }

        return "diaryManage/infoForm";
    }

    @PostMapping({"/updateDiaryInfo.do"})
    @ResponseBody
    public Map<String, Object> updateDiaryInfo(DiaryManageDto param, @LoginMember SessionMember member) throws Exception {
        param.setMember(member);
        Map<String, Object> resultMap = new HashMap<>();
        Object result = diaryManageService.updateDiaryInfo(param);
        resultMap.put("result",result);
        return resultMap;
    }

}


