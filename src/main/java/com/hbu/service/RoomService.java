package com.hbu.service;

import com.hbu.entity.RefConference;
import com.hbu.models.FreeModel;
import com.hbu.models.RoomModel;

import java.util.List;

public interface RoomService {
    public List<RoomModel> show();//跨系
    public List<RoomModel> show1(long number);//本系
    public List<RoomModel> show3();//后台

    public List<FreeModel> freeRoomShow(String date);//空闲会议室查询
    public List<FreeModel> freeRoomShow2(String date);//空闲会议室查询

    public int insert(RoomModel model);

    public int update(RoomModel model);

    public int del(long roomId);


}
