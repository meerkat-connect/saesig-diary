package com.saesig.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdoptionListResponseDto {
    private String title;

    private String gender;

    private String animalDivision1;

    private String animalDivision2;

    private String adoptedMemberName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime adoptedCompletedAt;
}
