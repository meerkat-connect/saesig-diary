package com.saesig.api.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailSender;

    @Override
    public void sendMail(MailDto mailDto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setFrom(emailSender);
            messageHelper.setSubject(mailDto.getSubject());
            messageHelper.setTo(mailDto.getToAddress());

            Context context = new Context();
            context.setVariables(mailDto.getParameters());
            // template 속성에 넣고자 하는 이메일 템플릿 주소를 넣으면 됨 (ex. mail/codeTemplate)
            String template = templateEngine.process(mailDto.getTemplate(), context);
            messageHelper.setText(template, true);
            // cid 방식으로 이미지 추가
            messageHelper.addInline("main_logo", new ClassPathResource("static/main_logo.png"));
            messageHelper.addInline("main_bg"  , new ClassPathResource("static/main_bg.png"));
            messageHelper.addInline("bottom_logo" , new ClassPathResource("static/bottom_logo.png"));

            mailSender.send(message);

            log.info("메일 전송 완료");
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
