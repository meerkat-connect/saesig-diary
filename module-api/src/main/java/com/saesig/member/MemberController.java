package com.saesig.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MemberController {
/*
    @GetMapping("/api/member/oauth2ClientCallback/google")
    public String googleOauth(){
        System.out.println("MemberController.googleOauth");
        return null;
    }
*/

    @GetMapping("/oauth2/login")
    public String login() {
        return "oauth/login";
    }
}
