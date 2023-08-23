package com.saesig.common.mybatis;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataTablesDto {

    // draw            : DataTables의 Data 순서 보장하기 위한 객체이다. 1부터 시작하며 해당 tables 하단의 페이지번호와는 무관하다.
    // recordsTotal    : 필터링 전 총 Total Count이다.
    // recordsFiltered : 필터링 후 총  Total Count이다. DataTalbles의 필터기능을 이용하여 paging을 할 경우 사용되는것 같다. 해당 프로젝트에선 사용하지 않고있다.
    // data            : DataTables를 paging 하기 위해선 해당 Dto를 return 해줘야하는데 목록 data를 여기에 담아서 반환해주면 된다.

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;

    private List data;

    public List getData(){
        if(CollectionUtils.isEmpty(data)){
            data = new ArrayList();
        }
        return data;
    }
}