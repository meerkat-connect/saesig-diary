package com.saesig.api.mail;

import com.saesig.config.ThymeleafConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
public class MailContorller {
    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ThymeleafConfig .class);

    @Value("${mail.user-email}")
    private static String userEmail;

    @Value("${mail.user-pw}")
    private static String userPw;

    @Value("${mail.smtp-host}")
    private static String smtpHost;

    @Value("${mail.smtp-port}")
    private static int smtpPort;

    public static void Send() throws Exception {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", smtpHost);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userEmail, userPw);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userEmail));

            Map<String, Object> variables = new HashMap<>();
            variables.put("name", "서정도");

            Context context = new Context();
            context.setVariables(variables);

            TemplateEngine templateEngine = applicationContext.getBean(TemplateEngine.class);
            String template = templateEngine.process("mail/emailtemp", context);


            // 받는 이메일
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("wonjjong.dev@gmail.com")
            );

            // 제목
            message.setSubject("새식일기 테스트 메일입니다.");

            // 내용
            message.setContent(template, "text/html; charset=euc-kr");

            // 발송
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}