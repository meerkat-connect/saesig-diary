package saesigDiary.role;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DataTablesResponseDto{
    private int draw;
    private Long recordsTotal;
    private Long recordsFiltered;

    List<?> list = new ArrayList<>();

    public DataTablesResponseDto(Page<?> pageInfo, List<?> list) {
        this.draw = pageInfo.getPageable().getPageNumber();
        this.recordsTotal = pageInfo.getTotalElements();
        this.recordsFiltered = pageInfo.getTotalElements();
        this.list = list;
    }
}
