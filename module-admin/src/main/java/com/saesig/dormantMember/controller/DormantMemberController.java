package com.saesig.dormantMember.controller;

import com.saesig.dormantMember.dto.DormantMemberRequestDto;
import com.saesig.dormantMember.dto.DormantMemberResponseDto;
import com.saesig.dormantMember.service.DormantMemberService;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/dormant-member")
public class DormantMemberController {
    private final DormantMemberService dormantMemberService;

    @GetMapping("/view")
    public String dormantMemberListView() {
        return "dormantMember/view";
    }

    @GetMapping("")
    @ResponseBody
    public DataTablesResponseDto findAll(@ModelAttribute DormantMemberRequestDto request) {
        return dormantMemberService.findAll(request);
    }

    @GetMapping("/{id}")
    public String dormantMemberDetailView(@PathVariable Long id, Model model) {
        DormantMemberResponseDto dormantMember = dormantMemberService.findById(id);
        model.addAttribute("dormantMember", dormantMember);
        return "dormantMember/detail";
    }

    @PostMapping("/{id}/release")
    public Long releaseDormantMember(@PathVariable Long id) {
        return id;
    }

}
