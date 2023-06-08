package saesigDiary.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import saesigDiary.config.ThymeleafConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
public class MailContorller {

    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ThymeleafConfig .class);

    // 구글 이메일
    static final String user_email= "eamil";
    // 구글 비번
    static final String user_pw = "앱 비밀번호";

    static final String smtp_host = "smtp.gmail.com";
    static final int smtp_port = 465;  // TLS : 587, SSL : 465

    public static void Send() throws Exception {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtp_host);
        props.put("mail.smtp.port", smtp_port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", smtp_host);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user_email, user_pw);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user_email));

            Map<String, Object> variables = new HashMap<>();
            variables.put("name", "안진한");

            Context context = new Context();
            context.setVariables(variables);

            TemplateEngine templateEngine = applicationContext.getBean(TemplateEngine.class);
            String template = templateEngine.process("mail/emailtemp", context);


            // 받는 이메일
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("email, email")
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