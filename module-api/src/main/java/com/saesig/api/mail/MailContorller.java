package com.saesig.api.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MailContorller {
    private final MailService mailService;

    @GetMapping("/sendMail")
    @ResponseBody
    public String sendMail(@RequestParam String memberEmail) {
        MailDto test = mailService.createMail("TEST", memberEmail);
        mailService.sendMail(test);

        return "ok";
    }
}