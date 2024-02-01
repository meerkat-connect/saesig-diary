package com.saesig.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class CommonController {
    private final DashBoardService dashBoardService;

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String failureMessage, Model model) {
        model.addAttribute("failureMessage", failureMessage);

        return "common/login";
    }

    @GetMapping({"", "/"})
    public String adminMain() {
        return "common/main";
    }
}
