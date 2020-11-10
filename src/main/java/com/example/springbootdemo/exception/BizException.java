package com.example.springbootdemo.exception;

import lombok.Data;

@Data
public class BizException extends RuntimeException {

    //错误码
    protected String errorCode;
    //错误信息
    protected String errorMsg;

    public BizException() {
    }

    public BizException(BaseErrorInfoInterface baseErrorInfoInterface) {
        super(baseErrorInfoInterface.getResultCode());
        this.errorCode=baseErrorInfoInterface.getResultCode();
        this.errorMsg=baseErrorInfoInterface.getResultMsg();
    }

    public BizException(BaseErrorInfoInterface baseErrorInfoInterface,Throwable cause) {
        super(baseErrorInfoInterface.getResultCode(),cause);
        this.errorCode=baseErrorInfoInterface.getResultCode();
        this.errorMsg=baseErrorInfoInterface.getResultMsg();
    }

    public BizException(String message) {
        super(message);
        this.errorMsg=message;
    }

    public BizException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg,Throwable cause){
        super(errorCode,cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


}
