package com.saesig.api.mail;

import com.saesig.config.ThymeleafConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MailContorller {
    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ThymeleafConfig .class);
    private final MailService mailService;

    @GetMapping("/sendMail")
    @ResponseBody
    public String sendMail() {
        MailDto test = mailService.createMail("test", "wonjjong.dev@gmail.com");
        mailService.sendMail(test);

        return "ok";
    }
}