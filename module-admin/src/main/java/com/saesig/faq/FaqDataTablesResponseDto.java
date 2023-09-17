package com.saesig.faq;

import com.saesig.role.DataTablesResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FaqDataTablesResponseDto extends DataTablesResponseDto {
    private Long minOrd;
    private Long maxOrd;

    public FaqDataTablesResponseDto(Page<?> pageInfo, List<?> data, Long minOrd, Long maxOrd) {
        super(pageInfo, data);
        this.minOrd = minOrd;
        this.maxOrd = maxOrd;
    }
}
