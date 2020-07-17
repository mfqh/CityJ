package com.whut.cityj.util;

/**
 * 状态工具类，辅助判断
 */
public class StateUtil {

    //登陆状态
    //未登陆
    public final static String LOGIN_NOT = "0";

    //习题状态
    //填空题
    public final static Integer TYPE_COMPLETION = 0;
    //选择题
    public final static Integer TYPE_CHOICE = 1;

    //试卷状态
    //未到时间
    public final static Integer TIME_BEFORE = 0;
    //试卷过期
    public final static Integer TIME_AFTER = 1;

    //分割符
    public final static String QUES_DIVISION ="$";

    //操作状态
    //成功
    public final static Integer SUCCESS_STATE = 1;
    //失败
    public final static Integer FAIL_STATE = 0;

}
