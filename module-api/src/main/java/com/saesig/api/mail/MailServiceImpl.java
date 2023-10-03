package com.saesig.api.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


    @Override
    public void sendMail(MailDto mailDto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setFrom(mailDto.getFromAddress());
            messageHelper.setSubject(mailDto.getSubject());
            messageHelper.setTo(mailDto.getToAddress());

            Context context = new Context();
            context.setVariables(mailDto.getParameters());
            String template = templateEngine.process(mailDto.getTemplate(), context);
            messageHelper.setText(template, true);

            mailSender.send(message);

            log.info("메일 전송 완료");
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
