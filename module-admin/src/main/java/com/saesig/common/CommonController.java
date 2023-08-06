package com.saesig.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {
    @GetMapping("/admin/login")
    public String login(@RequestParam(required = false) Boolean error,
                        @RequestParam(required = false) String errorMessage,
                        Model model) {

        model.addAttribute("error", error);
        model.addAttribute("errorMessage", errorMessage);

        return "common/login";
    }
}
