package com.saesig.templateManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class templateManageController {

    @GetMapping({"/templateManage","/templateManage/templateManageList.html"})
    public String templateManageList() {
        return "/templateManage/templateManageList";
    }

    @GetMapping({"/templateManage/templateManageForm.html"})
    public String templateManageForm() {
        return "/templateManage/templateManageForm";
    }
}
