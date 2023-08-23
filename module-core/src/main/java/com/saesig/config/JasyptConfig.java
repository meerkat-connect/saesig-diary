package com.saesig.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
    @Value("${jasypt.encryptorKey}")
    private String encryptorKey;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig encryptorConfig = new SimpleStringPBEConfig();

        encryptorConfig.setPassword(encryptorKey);
        encryptorConfig.setAlgorithm("PBEWithMD5AndDES");
        encryptorConfig.setKeyObtentionIterations("1000");
        encryptorConfig.setPoolSize("1");
        encryptorConfig.setProviderName("SunJCE");
        encryptorConfig.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        encryptorConfig.setStringOutputType("base64");
        encryptor.setConfig(encryptorConfig);

        return encryptor;
    }
}
