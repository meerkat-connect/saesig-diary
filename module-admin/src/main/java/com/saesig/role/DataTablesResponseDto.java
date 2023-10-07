package com.saesig.role;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DataTablesResponseDto {
    private Long recordsTotal;
    private Long recordsFiltered;
    private int pageNumber;
    private int pageSize;
    List<?> data = new ArrayList<>();

    public DataTablesResponseDto(Page<?> pageInfo, List<?> data) {
        this.recordsTotal = pageInfo.getTotalElements();
        this.recordsFiltered = pageInfo.getTotalElements();
        this.pageNumber = pageInfo.getNumber();
        this.pageSize = pageInfo.getSize();
        this.data = data;
    }
}
