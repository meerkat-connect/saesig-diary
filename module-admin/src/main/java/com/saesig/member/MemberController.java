package com.saesig.member;

import com.saesig.common.RequestDto;
import com.saesig.domain.member.Member;
import com.saesig.domain.templateManage.SendCategory;
import com.saesig.error.ErrorCode;
import com.saesig.error.NicknameDuplicateException;
import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.role.DataTablesResponseDto;
import com.saesig.templateManage.TemplateManageDto;
import com.saesig.templateManage.TemplateManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/members")
public class MemberController {
    private final MemberService memberService;
    private final TemplateManageService templateManageService;
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


    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long insertMember(@RequestBody @Valid MemberInsertDto memberInsertDto) {
        return memberService.insertMember(memberInsertDto);
    }

    @GetMapping("/insert")
    public String insertForm() {
        return "member/insert";
    }

    @GetMapping("/{id}")
    public String entrypointOfDetailView(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "member/entrypoint";
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long updateMember(@PathVariable Long id, @RequestBody @Valid MemberUpdateDto memberUpdateDto) {
        Optional<Member> byNickname = memberService.findByNickname(memberUpdateDto.getNickname());
        if (byNickname.isPresent()) {
            Member member = byNickname.get();
            if (!member.getId().equals(id)) {
                throw new NicknameDuplicateException(ErrorCode.EMAIL_DUPLICATED);
            }
        }

        return memberService.updateMember(id, memberUpdateDto);
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
        return !memberService.isNicknameDuplicate(nickname);
    }

    @GetMapping("/isEmailDuplicate")
    @ResponseBody
    public boolean isEmailDuplicate(@RequestParam String email) {
        return !memberService.isEmailDuplicate(email);
    }

    @GetMapping("/emailSendModal")
    public String emailSendModal(Model model) {
        List<TemplateManageDto> templates = templateManageService.selectTemplateListByCategory(SendCategory.batch);
        model.addAttribute("templates", templates);
        return "member/emailSendModal";
    }

    @PostMapping("/emailSendModal/send")
    @ResponseBody
    public Long sendEmail(@RequestParam Long[] memberIds, @RequestParam String message) {
        return memberService.sendMail(memberIds, message);
    }
}
