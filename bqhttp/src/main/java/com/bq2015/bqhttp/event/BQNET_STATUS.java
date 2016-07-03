package com.bq2015.bqhttp.event;

/************************************************************
 * Author:  bq2015
 * Description:     // 模块描述
 * Date: 2016/5/31
 ************************************************************/
public enum BQNET_STATUS {

    YD_UNKOWN_ERROR(-1),//未知错误
    JSONPARSE_FAIL(-2),//JSON数据解析失败

    OK(200),//正常请求，无语义结果

    NO_CODE(21),//服务器返回信息中没有code字段
    NO_MORE_DATA(40),//没有更多数据
    TOKEN_VERIFY_FAILED(100),//请重新登录
    TOKEN_OVERDUE(101),//Token过期
    ACCOUNT_LOGINED(102),//用户已经登录
    MISSING_PARAMETERS(300),//缺少参数
    SERVER_ERROR(500),//服务器错误，请重试

    NO_NET(10086);//当前无网络

    private BQNET_STATUS(int value) {
        this.value = value;
    }

    private final int value;

    public int getValue() {
        return value;
    }
}
