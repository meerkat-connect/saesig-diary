package com.saesig.policy;

import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import com.saesig.global.enumCode.EnumMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/policy")
public class PolicyController {
    private final EnumMapperFactory enumFactory;
    @Autowired
    private PolicyService policyService;
    @GetMapping("/view")
    public String policyView(Model model, @LoginMember SessionMember member) {
        model.addAttribute("policyCategories", enumFactory.get("policyCategory"));
        model.addAttribute("member", member);
        return "policy/view";
    }

    @RequestMapping(value = "/getPolicyByType.do")
    @ResponseBody
    public PolicyDto getInquiryList(@RequestParam String select_type) {
        PolicyDto result = policyService.getPolicyByType(select_type);
        if (result == null){
            result = new PolicyDto();
        }
        return result;
    }

    @RequestMapping(value = "/changePolicy.do")
    @ResponseBody
    public boolean ChangePolicy(@RequestBody PolicyDto param, @LoginMember SessionMember user) {
        param.setModifiedBy(user.getId());
        return policyService.ChangePolicy(param);
    }

}
