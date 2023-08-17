package com.saesig.api.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MemberApiController {

    @GetMapping("/oauth2/login")
    public String login() {
        return "oauth/login";
    }

    @GetMapping("/test")
    public String test() {
        return "oauth/login";
    }
}
