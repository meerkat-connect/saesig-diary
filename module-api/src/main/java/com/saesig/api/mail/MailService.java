package com.saesig.api.mail;

public interface MailService {

    public MailDto createMail(String tmpPassword, String memberEmail);

    public void sendMail(MailDto mailDto);
}
