package com.saesig.api.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
            // TODO 이메일 템플릿 적용 방식 확인 필요, 현재는 일반 문자열로 전송
            // String template = templateEngine.process(mailDto.getTemplate(), context);
            messageHelper.setText(mailDto.getMessage(), false);

            mailSender.send(message);

            log.info("메일 전송 완료");
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
