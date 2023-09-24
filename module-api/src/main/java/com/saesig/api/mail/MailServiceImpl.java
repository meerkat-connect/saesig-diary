package com.saesig.api.mail;

import com.saesig.config.ThymeleafConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ThymeleafConfig.class);

    private final JavaMailSender mailSender;
    private static final String title = "미어캣 임시 비밀번호 안내 이메일입니다.";
    private static final String message = "안녕하세요. 미어캣 임시 비밀번호 안내 메일입니다. "
            +"\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요."+"\n";
    private static final String fromAddress = "meerkat@gmail.com";
    /** 이메일 생성 **/
    @Override
    public MailDto createMail(String tmpPassword, String memberEmail) {

        MailDto mailDto = MailDto.builder()
                .toAddress(memberEmail)
                .title(title)
                .message(message + tmpPassword)
                .fromAddress(fromAddress)
                .build();

        log.info("메일 생성 완료");
        return mailDto;
    }

    /** 이메일 전송 **/
    @Override
    public void sendMail(MailDto mailDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mailDto.getToAddress());
        mailMessage.setSubject(mailDto.getTitle());
        mailMessage.setText(mailDto.getMessage());
        mailMessage.setFrom(mailDto.getFromAddress());
        mailMessage.setReplyTo(mailDto.getFromAddress());

        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mailSender.send(mailMessage);

            Map<String,Object> parameters = new HashMap<>();
            Context context =new Context();

            TemplateEngine templateEngine = applicationContext.getBean(TemplateEngine.class);
            String template = templateEngine.process("mail/emailtemp", context);
            mimeMessageHelper.setText(template, true);
            mimeMessageHelper.setCc("meerkat@gmail.com");
            mimeMessageHelper.setTo("wonjjong.dev@gmail.com");
            mailSender.send(mimeMessage);
            log.info("메일 전송 완료");
        }
        catch(Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
