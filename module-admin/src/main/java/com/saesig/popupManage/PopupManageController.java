package com.saesig.popupManage;

import com.saesig.banner.BannerDto;
import com.saesig.banner.BannerService;
import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.common.Constant;
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
@RequestMapping("/admin/popup")
public class PopupManageController {


    private final PopupManageService popupManageService;

    private final EnumMapperFactory enumMapperFactory;


    @GetMapping("view")
    public String popupList(Model model) throws Exception {
        model.addAttribute("exposureLocation", enumMapperFactory.get("exposureLocation"));
        model.addAttribute("buttonOption", enumMapperFactory.get("buttonOption"));

        return "popupManage/view";
    }

    @GetMapping({"/{id}/form", "/form"})
    public String form(@PathVariable(required = false) Optional<Long> id, Model model) throws Exception {
        model.addAttribute("exposureLocation", enumMapperFactory.get("exposureLocation"));
        model.addAttribute("buttonOption", enumMapperFactory.get("buttonOption"));

        if (id.isPresent()) {
            PopupManageDto popup = popupManageService.selectPopup(id.get());
            model.addAttribute("popup", popup);

            return "popupManage/form";
        }else {
            PopupManageDto popup = new PopupManageDto();
            model.addAttribute("popup", popup);

            return "popupManage/form";
        }
    }

    @GetMapping(value = "selectPopupList.do")
    @ResponseBody
    public Map<String, Object> selectBannerList(PopupManageDto pmd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        List<PopupManageDto> result = popupManageService.selectPopupList(pmd);

        resultMap.put("list", result);

        return resultMap;
    }

    @PostMapping("changeIsEnabled.do")
    @ResponseBody
    public Map<String, Object> changeIsEnabled(@LoginMember SessionMember member, @RequestBody PopupManageDto pmd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        if(pmd.getIsEnabled().equals('Y')) {
            int ord = popupManageService.selectOrd();
            pmd.setOrd((long) ord);
        }else {
            pmd.setOrd(null);
        }

        pmd.setMember(member);
        int retVal = 0;
        retVal = popupManageService.changeIsEnabled(pmd);

        if (retVal <= 0) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }

        return resultMap;
    }

    @PostMapping("insertForm.do")
    @ResponseBody
    public Map<String, Object> insertForm(@LoginMember SessionMember member, PopupManageDto pmd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        if(pmd.getIsEnabled().equals('Y')) {
            int ord = popupManageService.selectOrd();
            pmd.setOrd((long) ord);
        }

        pmd.setMember(member);
        int retVal = 0;
        retVal = popupManageService.insertForm(pmd);

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
    public Map<String, Object> updateForm(@LoginMember SessionMember member, PopupManageDto pmd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        pmd.setMember(member);
        int retVal = 0;
        retVal = popupManageService.updateForm(pmd);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }

        return resultMap;
    }

    @PostMapping("deleteItem.do")
    @ResponseBody
    public Map<String, Object> deleteItem(Long id) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        int retVal = 0;
        retVal = popupManageService.deleteItem(id);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패했습니다.");
        }

        return resultMap;
    }

    @PostMapping(value = "updateBannerSort.do")
    @ResponseBody
    public Map<String, Object> updateBannerSort(@RequestBody PopupManageDto pmd, @LoginMember SessionMember member) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        pmd.setMember(member);

        int retVal = 0;
        retVal = popupManageService.updatePopupSort(pmd);

        if (retVal < 0) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }

}
