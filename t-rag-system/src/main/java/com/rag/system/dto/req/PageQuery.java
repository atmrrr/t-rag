package com.rag.system.dto.req;

public class PageQuery {

    private Integer pageSize;

    private Integer pageNo;

    public Integer getPageSize(){

        if (pageSize == null || pageSize < 0)
            return 10;

        if (pageSize > 25)
            return 25;

        return pageSize;

    }

    public Integer getPageNo() {
        if (pageNo == null || pageNo < 0)
            return 1;

        return pageNo;
    }
}
