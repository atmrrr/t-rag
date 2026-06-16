package com.rag.common.dto.error;

import lombok.Getter;

/**
 * crm 50
 * system 51
 * rag 52
 */
public enum ResponseCode {

    SUCCESS(20000, "success"),
    SYSTEM_ERROR(50000, "系统错误"),
    NO_LOGIN(40001,"未登录"),
    NO_PROMISES(400003, "无权限"),
    NO_INFO(400004,"查询数据不存在"),
    ERROR_PASSWORD(50001, "密码错误"),
    ERROR_PARAM(50002, "参数错误"),
    REPEAT_TENANT(51000, "租户已存在"),
    ;

    @Getter
    private Integer code;
    @Getter
    private String meg;

    ResponseCode(Integer code, String meg){
        this.code = code;
        this.meg = meg;
    }


}
