package com.tjr.tragcommon.config;

import com.tjr.tragcommon.dto.error.ResponseCode;
import com.tjr.tragcommon.dto.resp.BaseResponse;
import com.tjr.tragcommon.exception.BusinessException;
import com.tjr.tragcommon.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<Void> handleBusinessException(BusinessException e){

        return ResultUtil.create(e.getCode(), e.getMessage(),null);
    }


    @ExceptionHandler(Exception.class)
    public BaseResponse<Void> handleException(Exception e){

        return ResultUtil.create(ResponseCode.SYSTEM_ERROR.getCode(), e.getMessage(),null);
    }

}
