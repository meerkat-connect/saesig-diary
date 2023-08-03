package com.saesig.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
    @GetMapping("/admin/login")
    public String login() {
        return "common/login";
    }
}
