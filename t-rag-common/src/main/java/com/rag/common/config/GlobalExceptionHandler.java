package com.rag.common.config;

import com.rag.common.dto.error.ResponseCode;
import com.rag.common.dto.resp.BaseResponse;
import com.rag.common.exception.BusinessException;
import com.rag.common.util.ResultUtil;
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
