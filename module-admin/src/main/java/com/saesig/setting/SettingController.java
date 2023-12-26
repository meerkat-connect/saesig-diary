package com.saesig.setting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {
    @GetMapping("/admin/setting")
    public String settingView() {
        return "setting/view";
    }
}
