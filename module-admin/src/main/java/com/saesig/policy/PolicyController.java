package com.saesig.policy;

import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/policy")
public class PolicyController {
    private final EnumMapperFactory enumFactory;
    @GetMapping("/view")
    public String policyView(Model model) {
        model.addAttribute("policyCategories", enumFactory.get("policyCategory"));
        return "policy/view";
    }
}
