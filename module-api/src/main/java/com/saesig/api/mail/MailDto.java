package com.saesig.api.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MailDto {
    private String toAddress;
    private String fromAddress;
    private String message;
    private String title;

    @Builder
    public MailDto(String toAddress, String fromAddress, String message, String title) {
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.message = message;
        this.title = title;
    }
}
