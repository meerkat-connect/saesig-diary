package com.saesig.saesigManage;

import com.saesig.common.mybatis.RequestDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportingDto extends RequestDto {
    private Long id;
    private String adopt_id;
    private String category;
    private String content;
    private String member_id;
    private LocalDateTime modified_at;
    private Long modified_by;
    private LocalDateTime created_at;
    private Long created_by;

}
