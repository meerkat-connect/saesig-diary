package com.saesig.member;

import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private String signup_method;
    private String nickname;
    private String status;
    private String joined_at;
    private String modified_at;
    private String modified_by;
    private String created_at;
    private String created_by;
    private String service_agreement;
    private String privacy_agreement;
    private String location_service_agreement;
    private String marketing_service_agreement;
}
