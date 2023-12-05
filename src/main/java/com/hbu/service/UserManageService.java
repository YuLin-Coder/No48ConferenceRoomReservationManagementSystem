package com.hbu.service;

import com.github.pagehelper.PageInfo;
import com.hbu.entity.TUser;
import com.hbu.models.RoomModel;

import java.util.List;

public interface UserManageService {
    PageInfo<TUser> show(int page);

    public int insert(TUser t);

    public int update(TUser t);

    public int del(int userId);

    List<TUser> newuser();



}
