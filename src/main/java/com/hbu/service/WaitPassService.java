package com.hbu.service;

import com.hbu.models.WaitPassModel;

import java.util.List;

public interface WaitPassService {
     List<WaitPassModel> showAll();//管理员显示
    
     List<WaitPassModel> showAll2();//系主任页面显示

     int waitpass1(int appointmentId);//管理员审核,通过
     int waitnopass1(int appointmentId,String reason);//管理员审核，不通过
    

     int waitpass2(int appointmentId);//系主任审核(暂时没做)


    List<WaitPassModel> showNoPass();

     /**
      * 新用户注册
      */

     int userpass(int appointmentId);//管理员审核,通过
     int usernopass(int appointmentId,String reason);//管理员审核，不通过
}
