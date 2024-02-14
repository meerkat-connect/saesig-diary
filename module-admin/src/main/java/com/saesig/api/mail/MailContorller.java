package com.saesig.api.mail;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class MailContorller {
    private final MailService mailService;

    @PostMapping("/sendMail")
    public void sendMail(@RequestBody MailDto mailDto) {
        mailService.sendMail(mailDto);
    }
}
