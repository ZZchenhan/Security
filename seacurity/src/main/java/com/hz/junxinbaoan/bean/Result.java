package com.hz.junxinbaoan.bean;

/**
 * Created by 86936 on 2018/7/2.
 *
 * @QQ 869360026
 */

public class Result<T> {
    private String code;
    private String message;
    private String errorType;
    private T result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
