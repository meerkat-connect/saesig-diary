package com.saesig.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdoptedListResponseDto {
    private String title;

    private String animalDivision1;

    private String animalDivision2;

    private String gender;

    private String adoptionMemberName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime adoptedCompletedAt;

}
