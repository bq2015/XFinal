package com.bq2015.bqhttp.bqnet;

/************************************************************
 * Author:  bq2015
 * Description: 自定义网络异常类
 * Date: 2016/6/1
 ************************************************************/
public class BQNetUnkownException extends Exception {

    private int    errorCode;//错误代码
    private String errorMsg; //错误信息

    public BQNetUnkownException() {
        super();
    }

    public BQNetUnkownException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg  = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
