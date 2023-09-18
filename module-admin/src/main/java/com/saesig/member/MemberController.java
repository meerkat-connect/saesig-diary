package com.saesig.member;

import com.saesig.common.RequestDto;
import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public DataTablesResponseDto findAll(@ModelAttribute MemberRequestDto request) {
        return memberService.findAll(request);
    }

    @GetMapping("/insert")
    public String insertForm() {
        return "member/insert";
    }

    @PostMapping(value ="/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String insertMember(@RequestBody MemberInsertDto memberInsertDto) {
        return null;
    }

    @GetMapping("/{id}")
    public String detailEntryPoint(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "member/entryPoint";
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public String updateMemberInfo(@PathVariable Long id) {
        return null;
    }


    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("memberId", id);
        model.addAttribute("memberStatus", enumMapperFactory.get("memberStatus"));
        model.addAttribute("memberDetail", memberService.findById(id));
        return "member/tab/detail";
    }

    // 분양 화면
    @GetMapping("/{id}/adoption")
    public String adoptionView(@PathVariable Long id, Model model) {
        model.addAttribute("memberId", id);
        return "member/tab/adoption";
    }

    @GetMapping("/{id}/adoption/list")
    @ResponseBody
    public DataTablesResponseDto findAdoptionList(@PathVariable Long id, @ModelAttribute RequestDto request) {
        return memberService.findAdoptionList(id, request);
    }


    // 입양 화면
    @GetMapping("/{id}/adopted")
    public String adoptedView(@PathVariable Long id, Model model) {
        model.addAttribute("memberId", id);
        return "member/tab/adopted";
    }

    @GetMapping("/{id}/adopted/list")
    @ResponseBody
    public DataTablesResponseDto findAdoptedList(@PathVariable Long id, @ModelAttribute RequestDto request) {
        return memberService.findAdoptedList(id, request);
    }


    // 신고 화면
    @GetMapping("/{id}/report")
    public String reportView(@PathVariable Long id, Model model) {
        model.addAttribute("memberId", id);
        return "member/tab/report";
    }

    @GetMapping("/{id}/report/list")
    @ResponseBody
    public DataTablesResponseDto findReportList(@PathVariable Long id, @ModelAttribute RequestDto request) {
        return memberService.findReportList(id, request);
    }

    // 차단 화면
    @GetMapping("/{id}/block")
    public String blockView(@PathVariable Long id, Model model) {
        model.addAttribute("memberId", id);
        return "member/tab/block";
    }

    @GetMapping("/{id}/block/list")
    @ResponseBody
    public DataTablesResponseDto findBlockList(@PathVariable Long id, @ModelAttribute RequestDto request) {
        return memberService.findBlockList(id, request);
    }

    @PostMapping("/{id}/generateTemporaryPassword")
    @ResponseBody
    public Long generateTemporaryPassword(@PathVariable Long id) {
        return memberService.generateTemporaryPassword(id);
    }

    @GetMapping("/isNicknameDuplicate")
    @ResponseBody
    public boolean isNicknameDuplicate(@RequestParam String nickname) {
        return memberService.isNicknameDuplicate(nickname);
    }
}
