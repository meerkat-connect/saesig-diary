package com.saesig.api.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
@Getter
@Setter
public class MailDto {
    private String toAddress;
    private String fromAddress;
    private String message;
    private String subject;
    private String template;
    private Map<String, Object> parameters = new HashMap<>();
    private Map<String, String> images = new HashMap<>();

    @Builder
    public MailDto(String toAddress, String fromAddress, String message, String subject, Map<String, Object> parameters, String template, Map<String, String> images) {
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.message = message;
        this.subject = subject;
        this.template = template;
        this.parameters = parameters;
        this.images = images;
    }
}
