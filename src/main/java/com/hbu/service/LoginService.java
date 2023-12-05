package com.hbu.service;

public interface LoginService {
     int backlogin(long number, String password);//登录不成功返回0

     int frontlogin(long number, String password);//登录不成功返回0

    int admineforgetpw(long number, String mailbox);//管理员忘记密码

    int userforgetpw(long number, String mailbox);//用户忘记密码

    int userregister(long number, String mailbox,String pw,String name,String department);//用户注册
}
