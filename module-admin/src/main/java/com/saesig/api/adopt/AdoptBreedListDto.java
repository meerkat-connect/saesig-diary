package com.saesig.api.adopt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.AdoptStopCategory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdoptBreedListDto {
    private Long id;
    private Long animalDivision1Id;
    private String category;
}
