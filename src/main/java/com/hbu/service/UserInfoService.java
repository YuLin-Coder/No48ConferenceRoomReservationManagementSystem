package com.hbu.service;

import com.hbu.entity.UserInfo;

public interface UserInfoService {

	int insert(UserInfo record);

    int insertSelective(UserInfo record);
    
    UserInfo selectByPrimaryKey(String userId);
    
    
    //登录第二步
    UserInfo selectByUserNameAndPassword(String userName,String userPassword);
}
