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
        return signService.signup(param);
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
    @PostMapping({"/find/password/{code}"})
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
    @GetMapping({"/sms/vaild/{code}"})
    public boolean isSmsCodeVaild(@PathVariable String code) {
        return this.verificationSmsCode.equals(code);
    }

    @Operation(summary="이메일 본인인증", description = "이메일 본인인증 코드 발송")
    @PostMapping({"/mail"})
    public void sendMail(@RequestBody MailDto mailDto) {
        // 메일 내용 세팅
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("messageTitle", "안녕하세요. 새식일기입니다:)");
        parameters.put("messageContent", "하단에 표기된 인증번호를 진행중인 화면에 입력해주세요.");
        parameters.put("verificationMailCode", this.verificationMailCode);
        mailDto.setParameters(parameters);
		this.mailService.sendMail(mailDto);
	}

    @Operation(summary="이메일 본인인증 유효성 확인", description = "이메일 본인인증 코드와 사용자 입력값 일치여부 체크")
    @GetMapping({"/mail/vaild/{code}"})
    public boolean isMailCodeVaild(@PathVariable String code) {
        return this.verificationMailCode.equals(code);
    }
}
