package com.saesig.member;

import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/members")
public class MemberController {
    private final MemberService memberService;
    private final EnumMapperFactory enumMapperFactory;

    @GetMapping("/view")
    public String memberListView(Model model) {
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

}
