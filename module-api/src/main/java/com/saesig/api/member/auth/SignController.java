package com.saesig.api.member.auth;

import com.saesig.api.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


@Tag(name="Sign Controller", description = "회원정보 관리")
@Slf4j
@RestController()
@RequestMapping(Constants.CONTEXT_PATH)
public class SignController {

    private final SignService signService;
    private final PasswordEncoder passwordEncoder;
    private final DefaultMessageService messageService;
    String verificationCode = String.format("%06d", new Random().nextInt(1000000));

    public SignController(SignService signService, PasswordEncoder passwordEncoder) {
        this.signService = signService;
        this.passwordEncoder = passwordEncoder;
        // TODO local/prod 인증키 분기 (prod: 유료 버전 변경)
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

    @Operation(summary="이메일 찾기", description = "본인인증 후 가입된 이메일 찾기")
    @GetMapping({"/find/email/{phoneNumber}"})
    public String findEmail(@PathVariable String phoneNumber) {
        return signService.findEmail(phoneNumber);
    }

    @Operation(summary="SMS 본인인증", description = "SMS 본인인증", parameters = {
            @Parameter(name = "phoneNumber", description = "수신자 전화번호", example = "01012341234") })
    @GetMapping({"/sms/{phoneNumber}"})
    public SingleMessageSentResponse sendSms(@PathVariable String phoneNumber) {
        Message message = new Message();
        message.setFrom("01066620321");    // 발신번호 (임시)
        message.setTo(phoneNumber);        // 수신번호
        message.setText(verificationCode); // 문자내용

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }

    @Operation(summary="SMS 본인인증 유효성 확인", description = "SMS 본인인증 문자와 사용자 입력값 일치여부 체크")
    @GetMapping({"/sms/check/{code}"})
    public boolean isSmsCodeVaild(@PathVariable String code) {
        return this.verificationCode.equals(code);
    }

}
