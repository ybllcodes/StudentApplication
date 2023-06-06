package com.ybllcodes.studentapplication.utils;

public class Constant {
    public static final String IP = "124.71.1.24";
    public static final String HOST = "http://"+IP+":";
    public static final Integer PORT = 8088;
    public static final String USER_PREFIX = "/hra/user";
    public static final String CONTROL_PREFIX = "/hra/control";

    public static final String USER_URL = Constant.HOST + Constant.PORT + Constant.USER_PREFIX;
    public static final String CONTROL_URL = Constant.HOST + Constant.PORT + Constant.CONTROL_PREFIX;

    public static final String BIND_PS = "/bindPS";
    public static final String UNBIND_PS = "/unbindPS";
    public static final String REGISTER_MAC = "/registermac";

    public static final String SET_RULE = "/setrule";
    public static final String GETMSG_FROM_S = "/getmsgfs";
    public static final String GETMSG_FROM_P = "/getmsgfp";

}
