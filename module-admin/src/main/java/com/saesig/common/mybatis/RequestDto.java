package com.saesig.common.mybatis;

import com.saesig.util.StringUtil;
import lombok.Data;

@Data
public class RequestDto {
    private String searchType;
    private String searchName;
    private Integer start;
    private Integer length;
    private Integer pageNum;

    public RequestDto(Integer start, Integer length, Integer pageNum) {
        this.start = start;
        this.length = length;
        this.pageNum = pageNum;
    }

    protected Integer draw;
    protected Integer recordsTotal;
    protected Integer rowNumber;

    public static final ORDER_DIRECTION ORDER_DIRECTION_ASC = ORDER_DIRECTION.asc;
    public static final ORDER_DIRECTION ORDER_DIRECTION_DESC = ORDER_DIRECTION.desc;

    public int pageNumber;
    public int rowPerPage;
    public int pageOffset;
    public String orderField;
    public ORDER_DIRECTION orderDirection;

    public enum ORDER_DIRECTION {
        asc, desc
    }

    public RequestDto() {
        this.chkParams();
    }

    public Integer getPageNumber(Integer start, Integer length) {
        this.pageNumber = (start/length);
        if(this.pageNumber == 0) {
            this.pageNumber = 1;
        }
        return this.pageNumber;
    }

    public void setOrderField(String orderField) {
        if (StringUtil.isSqlInjectionSafe(orderField)) {
            this.orderField = orderField;
        }
    }
    public void chkParams() {
        if (this.pageNumber == 0) {
            this.pageNumber = 1;
        }

        if (this.rowPerPage == 0) {
            this.rowPerPage = 10;
        }

        if (this.orderField == null || "".equals(this.orderField)) {
            this.orderField = "MODIFIED_AT";
        }

        if (this.orderDirection == null) {
            this.orderDirection = ORDER_DIRECTION.desc;
        }
    }
}
