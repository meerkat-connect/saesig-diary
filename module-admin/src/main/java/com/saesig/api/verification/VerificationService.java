package com.saesig.api.verification;


import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VerificationService {
    private Map<String, VerificationCode> verificationCodes = new ConcurrentHashMap<>();
    private static final long CODE_EXPIRATION_TIME = 10 * 60 * 1000; // 10 minutes

    public boolean verifyCode(String email, String code) {
        VerificationCode verificationCode = verificationCodes.get(email);
        if (verificationCode != null && verificationCode.getCode().equals(code) &&
                verificationCode.getExpirationTime() > System.currentTimeMillis()) {
            verificationCodes.remove(email); // Remove the code once it is verified
            return true;
        }
        return false;
    }

    private void removeExpiredCodes() {
        long currentTime = System.currentTimeMillis();
        verificationCodes.entrySet().removeIf(entry -> entry.getValue().getExpirationTime() <= currentTime);
    }

    public VerificationCode getVerificationCodeByEmail(String email) {
        return verificationCodes.get(email);
    }

    public String generateVerificationCode(String email) {
        String code = String.format("%06d", new Random().nextInt(1000000));
        this.verificationCodes.put(email, new VerificationCode(code));

        return code;
    }


    private static class VerificationCode {
        private final String code;
        private final long expirationTime;

        public VerificationCode(String code) {
            this.code = code;
            this.expirationTime = System.currentTimeMillis() + CODE_EXPIRATION_TIME;
        }

        public String getCode() {
            return code;
        }

        public long getExpirationTime() {
            return expirationTime;
        }
    }
}
