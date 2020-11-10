package com.example.springbootdemo.exception;

import com.example.springbootdemo.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    @ExceptionHandler(value = BizException.class)
    public ResultData bizExceptionHandle(BizException e){
        ResultData r=new ResultData();
        r.put("code",e.getErrorCode());
        r.put("msg",e.errorMsg);
        return r;
    }

    @ExceptionHandler(value = Exception.class)
    public ResultData exceptionHandle(Exception e){
        return ResultData.error();
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResultData noFoundHandle(Exception e){
        return ResultData.error(404,"路径不存在!");
    }
}
