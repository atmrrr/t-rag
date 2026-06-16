package com.rag.crm.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserInfoVo {

    private Long id;

    private String name;

    private Long tokens;

    private Integer roleId;

    private List<String> promisees;

    private Integer tenantId;

    private Integer deptId;

    private Long createUser;

    private Long updateUser;

    private Date uTime;

    private Date cTime;

}
