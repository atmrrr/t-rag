package com.rag.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class LoginUserInfo {

    private Long userId;

    private Long tenantId;

    private String deptId;

    private Long roleId;

    private List<Long> promiseId;

}
