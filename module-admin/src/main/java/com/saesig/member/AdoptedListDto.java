package com.saesig.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;

@Getter
public class AdoptedListDto {
    private String title;

    private String animalDivision1;

    private String animalDivision2;

    private String gender;

    private String adoptMemberName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date adoptedAt;

}
