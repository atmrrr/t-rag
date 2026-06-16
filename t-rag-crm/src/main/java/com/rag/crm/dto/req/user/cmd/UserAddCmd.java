package com.rag.crm.dto.req.user.cmd;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

//添加操作员方法
@Data
public class UserAddCmd implements Serializable {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotNull
    private Long roleId;

    @NotNull
    private String orgId;

    @NotNull
    private String deptId;

}
