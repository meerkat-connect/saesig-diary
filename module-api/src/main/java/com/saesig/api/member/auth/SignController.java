package com.saesig.api.member.auth;

import com.saesig.api.mail.MailDto;
import com.saesig.api.mail.MailService;
import com.saesig.api.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import com.saesig.error.VerificationCodeMismatchException;
import org.springframework.security.crypto.password.PasswordEncoder;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Tag(name="Sign Controller", description = "회원정보 관리")
@Slf4j
@RestController()
@RequestMapping(Constants.CONTEXT_PATH + "/sign")
public class SignController {

    private final SignService signService;
    private final PasswordEncoder passwordEncoder;
    private final DefaultMessageService messageService;
    private final MailService mailService;
    String verificationSmsCode = String.format("%06d", new Random().nextInt(1000000));
    String verificationMailCode = String.format("%06d", new Random().nextInt(1000000));

    public SignController(SignService signService, PasswordEncoder passwordEncoder, MailService mailService) {
        this.signService = signService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        // TODO local/prod 인증키 분기 (prod: 유료 버전 변경) 암호화해서 active 값에 따라 세팅되도록 변경 필요
        // 쿨SMS (SMS 발송용) - 계정 내 등록된 유효한 API 키, API Secret Key
        this.messageService = NurigoApp.INSTANCE.initialize("NCS4YSWXMANXRGLD", "ZHSJXFM0VRYMLZFRYQVRWMALZKOPBLNJ", "https://api.coolsms.co.kr");
    }

    @Operation(summary="회원가입 등록", description = "입력한 정보를 이용하여 회원가입을 진행")
    @PostMapping("/signup")
    public int signup(@RequestBody SignDto param) {
        param.setPassword(passwordEncoder.encode(param.getPassword())); // 비밀번호 암호화
        int result = signService.signup(param);
        if(result > 0) {
            // 회원가입 축하 메일 발송
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("messageTitle", param.getNickname() + "님 안녕하세요. 새식일기입니다:)");
            parameters.put("messageContent", "새식일기의 가족이 되신 것을 환영합니다.<br>새식일기와 함께 새로운 식구를 만나고 따뜻한 일상을 기록해 보세요.");

            Map<String, String> images = new HashMap<>();
            images.put("main_logo", "static/main_logo.png");
            images.put("main_bg", "static/main_bg.png");
            images.put("bottom_logo", "static/bottom_logo.png");

            MailDto mailDto = new MailDto(param.getEmail(), "", "", "새식일기의 가족이 되신것을 축하드립니다.", parameters, "mail/signupTemplate", images);
            this.mailService.sendMail(mailDto);
        }
        return result;
    }

    @Operation(summary="회원정보 중복검사", description = "이메일/닉네임 중복검사")
    @GetMapping({"/duplicate/{mode}/{value}"})
    public int duplicate(@PathVariable String mode, @PathVariable String value) {
        SignDto param = new SignDto();
        if("email".equals(mode)) {
            param.setEmail(value);
        } else if("nickname".equals(mode)){
            param.setNickname(value);
        }
        return signService.duplicate(param);
    }

    @Operation(summary="닉네임으로 이메일 찾기", description = "닉네임으로 가입된 이메일 찾기")
    @GetMapping({"/find/email/{nickname}"})
    public SignDto findEmailByNickname(@PathVariable String nickname) {
        return signService.findEmailByNickname(nickname);
    }

    @Operation(summary="SMS 본인인증으로 이메일 찾기", description = "SMS 본인인증으로 가입된 이메일 찾기")
    @GetMapping({"/find/email/{mobileNumber}/{code}"})
    public SignDto findEmailBySms(@PathVariable String mobileNumber, @PathVariable String code) {
        // SMS 본인인증문자 번호와 사용자 입력값 일치여부 체크
        if(!this.verificationSmsCode.equals(code)) {
            throw new VerificationCodeMismatchException("인증번호가 일치하지 않습니다", "code");
        }
        return signService.findEmailBySms(mobileNumber);
    }

    @Operation(summary="비밀번호 찾기(재설정)", description = "본인인증 후 비밀번호 재설정")
    @PutMapping({"/find/password/{code}"})
    public int updatePassword(@PathVariable String code, @RequestBody SignDto param) {
        // 메일 본인인증 번호와 사용자 입력값 일치여부 체크
        if(!this.verificationMailCode.equals(code)) {
            throw new VerificationCodeMismatchException("인증번호가 일치하지 않습니다", "code");
        }
        param.setPassword(passwordEncoder.encode(param.getPassword()));
        return signService.updatePassword(param);
    }

    @Operation(summary="SMS 본인인증", description = "SMS 본인인증 코드 발송", parameters = {
            @Parameter(name = "mobileNumber", description = "수신자 전화번호", example = "01012341234") })
    @GetMapping({"/sms/{mobileNumber}"})
    public SingleMessageSentResponse sendSms(@PathVariable String mobileNumber) {
        Message message = new Message();
        message.setFrom("01066620321");       // 발신번호 (임시)
        message.setTo(mobileNumber);          // 수신번호
        message.setText("[새식일기-인증번호] : " + this.verificationSmsCode); // 문자내용

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }

    @Operation(summary="SMS 본인인증 유효성 확인", description = "SMS 본인인증 문자와 사용자 입력값 일치여부 체크")
    @GetMapping({"/sms/valid/{code}"})
    public boolean isSmsCodeValid(@PathVariable String code) {
        return this.verificationSmsCode.equals(code);
    }

    @Operation(summary="이메일 본인인증", description = "이메일 본인인증 코드 발송")
    @PostMapping({"/email/{email}"})
    public void sendCodeMail(@PathVariable String email) {
        // 메일 내용 세팅
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("messageTitle", "안녕하세요. 새식일기입니다:)");
        parameters.put("messageContent", "하단에 표기된 인증번호를 진행중인 화면에 입력해주세요.");
        parameters.put("verificationMailCode", this.verificationMailCode);

        Map<String, String> images = new HashMap<>();
        images.put("main_logo", "static/main_logo.png");
        images.put("main_bg", "static/main_bg.png");
        images.put("bottom_logo", "static/bottom_logo.png");

        MailDto mailDto = new MailDto(email, "", "", "새식일기 인증코드입니다.", parameters, "mail/codeTemplate", images);
        mailDto.setParameters(parameters);
		this.mailService.sendMail(mailDto);
	}

    @Operation(summary="이메일 본인인증 유효성 확인", description = "이메일 본인인증 코드와 사용자 입력값 일치여부 체크")
    @GetMapping({"/email/valid/{code}"})
    public boolean isMailCodeValid(@PathVariable String code) {
        return this.verificationMailCode.equals(code);
    }

    @Operation(summary="회원탈퇴", description = "회원탈퇴")
    @DeleteMapping({"/resign/{id}"})
    public int resign(@PathVariable Long id, @RequestParam("email") String email, @RequestParam("nickname") String nickname) {

        int result = this.signService.resign(id);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("messageTitle", nickname + "님 새식일기와 좋은 추억 남기셨나요?");
        parameters.put("messageContent", "새식일기는 " + nickname + "님과 함께한 날들을 기억하며, 더 좋은 모습으로 찾아 뵐 수 있도록 하겠습니다.<br>"
                                       + "다시 만나는 그날까지 행복한 일들만 가득하세요:)");

        Map<String, String> images = new HashMap<>();
        images.put("main_logo", "static/main_logo.png");
        images.put("resign_bg", "static/resign_bg.png");
        images.put("bottom_logo", "static/bottom_logo.png");

        MailDto mailDto = new MailDto(email, "", "", "새식일기를 이용해주셔서 감사합니다.", parameters, "mail/resignTemplate", images);
        mailDto.setParameters(parameters);
        this.mailService.sendMail(mailDto);
        return result;
    }
}
