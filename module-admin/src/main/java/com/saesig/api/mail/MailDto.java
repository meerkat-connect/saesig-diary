package com.saesig.api.mail;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailDto {
    private String toAddress;

    private String fromAddress;

    private String message;

    private String subject;

    private String template;

    @Builder.Default
    private Map<String, Object> parameters = new HashMap<>();

    @Builder.Default
    private Map<String, String> images = new HashMap<>();
}
