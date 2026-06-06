package com.rag.common.dto.error;

import lombok.Getter;

public enum ResponseCode {

    SUCCESS(2000, "success"),
    SYSTEM_ERROR(5000, "系统错误");

    @Getter
    private Integer code;
    @Getter
    private String meg;

    ResponseCode(Integer code, String meg){
        this.code = code;
        this.meg = meg;
    }


}
