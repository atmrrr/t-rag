package com.tjr.tragcommon.util;

import com.tjr.tragcommon.dto.error.ResponseCode;
import com.tjr.tragcommon.dto.resp.BaseResponse;

public class ResultUtil {


    public static <T> BaseResponse create(Integer code, String msg, T data){

        return new BaseResponse(code, msg, data);
    }

    public static <T> BaseResponse create(ResponseCode responseCode, T data){

        return new BaseResponse(responseCode.getCode(), responseCode.getMeg(), data);
    }

    public static <T> BaseResponse success(T data){

        return create(ResponseCode.SUCCESS, data);
    }

}
