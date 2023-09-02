package com.saesig.member;

import com.saesig.common.RequestDto;
import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/members")
public class MemberController {
    private final MemberService memberService;
    private final EnumMapperFactory enumMapperFactory;

    @GetMapping("/view")
    public String listView(Model model) {
        model.addAttribute("memberStatus", enumMapperFactory.get("memberStatus"));
        model.addAttribute("signupMethod", enumMapperFactory.get("signupMethod"));

        return "member/view";
    }

    @GetMapping("")
    @ResponseBody
    public Map<String, Object> findAll(@ModelAttribute MemberRequestDto request) {
        DataTablesResponseDto dataTablesResponseDto = memberService.findAll(request);
        Map<String, Object> result = new HashMap<>();
        result.put("data", dataTablesResponseDto.getList());
        result.put("recordsTotal", dataTablesResponseDto.getRecordsTotal());
        result.put("recordsFiltered", dataTablesResponseDto.getRecordsFiltered());

        return result;
    }

    @GetMapping("/{id}")
    public String detailEntryPoint(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "member/entryPoint";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id) {
        return "member/tab/detail";
    }

    // 분양 화면
    @GetMapping("/{id}/adoption")
    public String adoptionView(@PathVariable Long id, Model model)
    {
        model.addAttribute("memberId", id);
        return "member/tab/adoption";
    }

    @GetMapping("/{id}/adoption/list")
    @ResponseBody
    public Map<String,Object> findAdoptionList(@PathVariable Long id, @ModelAttribute RequestDto request)
    {
        DataTablesResponseDto dataTablesResponseDto = memberService.findAdoptionList(id, request);
        Map<String, Object> result = new HashMap<>();
        result.put("data", dataTablesResponseDto.getList());
        result.put("recordsTotal", dataTablesResponseDto.getRecordsTotal());
        result.put("recordsFiltered", dataTablesResponseDto.getRecordsFiltered());

        return result;
    }


    // 입양 화면
    @GetMapping("/{id}/adopted")
    public String adoptedView(@PathVariable Long id, Model model) {
        model.addAttribute("memberId", id);
        return "member/tab/adopted";
    }

    @GetMapping("/{id}/adopted/list")
    @ResponseBody
    public Map<String,Object> findAdoptedList(@PathVariable Long id, @ModelAttribute RequestDto request) {
        DataTablesResponseDto dataTablesResponseDto = memberService.findAdoptedList(id, request);
        Map<String, Object> result = new HashMap<>();
        result.put("data", dataTablesResponseDto.getList());
        result.put("recordsTotal", dataTablesResponseDto.getRecordsTotal());
        result.put("recordsFiltered", dataTablesResponseDto.getRecordsFiltered());

        return result;
    }


    // 신고 화면
    @GetMapping("/{id}/report")
    public String reportView(@PathVariable Long id) {
        return "member/tab/report";
    }

    @GetMapping("/{id}/report/list")
    @ResponseBody
    public Map<String,Object> findReportList(@PathVariable Long id, @ModelAttribute RequestDto request) {
        DataTablesResponseDto dataTablesResponseDto = memberService.findReportList(id, request);
        Map<String, Object> result = new HashMap<>();
        result.put("data", dataTablesResponseDto.getList());
        result.put("recordsTotal", dataTablesResponseDto.getRecordsTotal());
        result.put("recordsFiltered", dataTablesResponseDto.getRecordsFiltered());

        return result;
    }

    // 차단 화면
    @GetMapping("/{id}/block")
    public String blockView(@PathVariable Long id) {
        return "member/tab/block";
    }
}
