package com.rag.system.dto.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class TenantSearchQuery extends PageQuery implements Serializable {

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 联系人
     */
    private String contact;


}
