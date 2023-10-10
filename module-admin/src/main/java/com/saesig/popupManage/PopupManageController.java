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


    private final BannerService bannerService;

    private final EnumMapperFactory enumMapperFactory;


    @GetMapping("view")
    public String popupList(Model model) throws Exception {
        model.addAttribute("exposureLocation", enumMapperFactory.get("exposureLocation"));

        return "popupManage/view";
    }

    @GetMapping({"/{id}/form", "/form"})
    public String form(@PathVariable(required = false) Optional<Long> id, Model model) throws Exception {
        model.addAttribute("exposureLocation", enumMapperFactory.get("exposureLocation"));

        if (id.isPresent()) {
            BannerDto banner = bannerService.selectBanner(id.get());
            model.addAttribute("banner", banner);

            return "popupManage/form";
        }else {
            BannerDto banner = new BannerDto();
            model.addAttribute("banner", banner);

            return "popupManage/form";
        }
    }

    @GetMapping(value = "selectBannerList.do")
    @ResponseBody
    public Map<String, Object> selectBannerList(BannerDto bd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        List<BannerDto> result = bannerService.selectBannerList(bd);

        resultMap.put("list", result);

        return resultMap;
    }

    @PostMapping("changeIsEnabled.do")
    @ResponseBody
    public Map<String, Object> changeIsEnabled(@LoginMember SessionMember member, @RequestBody BannerDto bd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        if(bd.getIsEnabled().equals('Y')) {
            int ord = bannerService.selectOrd();
            bd.setOrd((long) ord);
        }else {
            bd.setOrd(null);
        }

        bd.setMember(member);
        int retVal = 0;
        retVal = bannerService.changeIsEnabled(bd);

        if (retVal <= 0) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }

        return resultMap;
    }

    @PostMapping("insertForm.do")
    @ResponseBody
    public Map<String, Object> insertForm(@LoginMember SessionMember member, BannerDto bd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        if(bd.getIsEnabled().equals('Y')) {
            int ord = bannerService.selectOrd();
            bd.setOrd((long) ord);
        }

        bd.setMember(member);
        int retVal = 0;
        retVal = bannerService.insertForm(bd);

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
    public Map<String, Object> updateForm(@LoginMember SessionMember member, BannerDto bd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        bd.setMember(member);
        int retVal = 0;
        retVal = bannerService.updateForm(bd);

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
        retVal = bannerService.deleteItem(id);

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
    public Map<String, Object> updateBannerSort(@RequestBody BannerDto bd, @LoginMember SessionMember member) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        bd.setMember(member);

        int retVal = 0;
        retVal = bannerService.updateBannerSort(bd);

        if (retVal < 0) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }

}