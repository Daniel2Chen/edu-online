package com.huainian.eduonline.utils;

/**
 * FileName: DefaultResultOptions
 * Author: huainian.chen
 * Date: 2019/2/12 15:37
 * Description: 默认提示语
 */
public enum  DefaultResultOptions implements ResultOptions{

    EXCEPTION(404, "系统开小差了，请稍后重试"),
    SUCCESS(200, "操作成功"),
    FAIL(201, "操作失败"),
    OUTTIME(202, "您的身份过期了，请重新登录"),
    MISSPARAMTER(203, "您好像少提交了参数");
    private int code;
    private String msg;
    @Override
    public int getCode() {
        return this.code;
    }
    private DefaultResultOptions(int code,String msg){
       this.code = code;
       this.msg = msg;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
