package com.hbu.service.impl;

import com.hbu.dao.*;
import com.hbu.entity.*;

import com.hbu.models.AppointmentModel;
import com.hbu.models.FreeModel;
import com.hbu.models.RoomModel;
import com.hbu.service.RoomService;
import com.hbu.utils.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private TConferenceRoomAppointmentMapper tConferenceRoomAppointmentMapper;
    @Autowired
    private TConferenceRoomMapper tConferenceRoomMapper;
    @Autowired
    private RefConferenceMapper refConferenceMapper;
    @Autowired
    private DicConferenceRoomTypeMapper dicConferenceRoomTypeMapper;
    @Autowired
    private DicTimeMapper dicTimeMapper;
    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public List<RoomModel> show(){
        TConferenceRoomExample roomExample = new TConferenceRoomExample();
        TConferenceRoomExample.Criteria criteria =roomExample.createCriteria();
        criteria.andIsdelEqualTo(true);
        criteria.andStatusEqualTo(1);
        List<TConferenceRoom> list = tConferenceRoomMapper.selectByExample(roomExample);
 
        List<RoomModel> listmodel = new ArrayList<RoomModel>();
        for(int i=0;i<list.size();i++){
            RoomModel model = new RoomModel();
            model.setId(list.get(i).getId());
            model.setName(list.get(i).getName());
            model.setAddress(list.get(i).getAddress());
            model.setCapacity(list.get(i).getCapacity());
            model.setStatus(list.get(i).getStatus());
            model.setEquipment(list.get(i).getEquipment());
            //会议室type
            int code = list.get(i).getConferenceRoomTypeCode();
            DicConferenceRoomType  roomType= dicConferenceRoomTypeMapper.selectByCode(code);
            //roomType.getDescription();
            model.setType(roomType.getDescription());
            model.setAvailableTime(list.get(i).getAvailableTime());
           // roomType.getDirectorName();
            listmodel.add(model);
        }
        if(listmodel != null && listmodel.size() > 0) {
            return listmodel;
        }
        else {
            return null;
        }
    }
    //本系会议室显示
    @Override
    public List<RoomModel> show1(long number){
        String department = tUserMapper.selectDepartment(number);
        int departmentcode= dicConferenceRoomTypeMapper.selectByDescription(department);
        List<TConferenceRoom> list = tConferenceRoomMapper.selectByDepartment(departmentcode);
        List<RoomModel> listmodel = new ArrayList<RoomModel>();
        for(int i=0;i<list.size();i++){
            RoomModel model = new RoomModel();
            model.setId(list.get(i).getId());
            model.setName(list.get(i).getName());
            model.setAddress(list.get(i).getAddress());
            model.setCapacity(list.get(i).getCapacity());
            model.setStatus(list.get(i).getStatus());
            model.setEquipment(list.get(i).getEquipment());
            //会议室type
            int code = list.get(i).getConferenceRoomTypeCode();
            DicConferenceRoomType  roomType= dicConferenceRoomTypeMapper.selectByCode(code);
            //roomType.getDescription();
            model.setType(roomType.getDescription());
            model.setAvailableTime(list.get(i).getAvailableTime());
            // roomType.getDirectorName();
            listmodel.add(model);
        }
        if(listmodel != null && listmodel.size() > 0) {
            return listmodel;
        }
        else {
            return null;
        }
    }
    @Override
    public List<RoomModel> show3(){
        TConferenceRoomExample roomExample = new TConferenceRoomExample();
        TConferenceRoomExample.Criteria criteria =roomExample.createCriteria();
        criteria.andIsdelEqualTo(true);
        List<TConferenceRoom> list = tConferenceRoomMapper.selectByExample(roomExample);

        List<RoomModel> listmodel = new ArrayList<RoomModel>();
        for(int i=0;i<list.size();i++){
            RoomModel model = new RoomModel();
            model.setId(list.get(i).getId());
            model.setName(list.get(i).getName());
            model.setAddress(list.get(i).getAddress());
            model.setCapacity(list.get(i).getCapacity());
            model.setStatus(list.get(i).getStatus());
            model.setEquipment(list.get(i).getEquipment());
            //会议室type
            int code = list.get(i).getConferenceRoomTypeCode();
            DicConferenceRoomType  roomType= dicConferenceRoomTypeMapper.selectByCode(code);
            //roomType.getDescription();
            model.setType(roomType.getDescription());
            model.setAvailableTime(list.get(i).getAvailableTime());
            // roomType.getDirectorName();
            listmodel.add(model);
        }
        if(listmodel != null && listmodel.size() > 0) {
            return listmodel;
        }
        else {
            return null;
        }
    }

    @Override
    public int insert(RoomModel model){
        TConferenceRoom room = new TConferenceRoom();
        room.setName(model.getName());
        room.setAddress(model.getAddress());
        room.setCapacity(model.getCapacity());
        room.setEquipment(model.getEquipment());
        room.setStatus(model.getStatus());
        room.setAvailableTime(model.getAvailableTime());
        String type = model.getType();
        /**
         *前台入参是字符串类型的type，插入时需要从dic表中查出对应的code
         * 注意：xml中目前是模糊查询，如果前端变成下拉菜单的样式，就可以直接用等号
         * 2020/5/2
         */
        int code = dicConferenceRoomTypeMapper.selectByDescription(type);

        room.setConferenceRoomTypeCode(code);
        room.setIsdel(true);
        int num = tConferenceRoomMapper.insert(room);
        //不成功返回0
        if(num == 0) {
            return 0;
        }else {
            return num;
        }
    }

    @Override
    public int update(RoomModel model){
        long id = model.getId();
        TConferenceRoom room = new TConferenceRoom();
        room.setId(id);
        room.setName(model.getName());
        room.setAddress(model.getAddress());
        room.setCapacity(model.getCapacity());
        room.setAvailableTime(model.getAvailableTime());
        room.setEquipment(model.getEquipment());
        room.setStatus(model.getStatus());
        System.out.println(model.getStatus());
        room.setIsdel(true);
        String type = model.getType();
        /**
         *前台入参是字符串类型的type，插入时需要从dic表中查出对应的code
         * 注意：xml中目前是模糊查询，如果前端变成下拉菜单的样式，就可以直接用等号
         * 2020/5/2
         */
        int code = dicConferenceRoomTypeMapper.selectByDescription(type);
        room.setConferenceRoomTypeCode(code);


        int num = tConferenceRoomMapper.updateByPrimaryKeySelective(room);
        //不成功返回0
        if(num == 0) {
            return 0;
        }else {
            return num;
        }
    }
    @Override
    public int del(long roomId){
        TConferenceRoom room =new TConferenceRoom();
        room.setId(roomId);
        room.setIsdel(false);
        /**
         * 判断预约表，只要有相关会议就不能删
         */
        TConferenceRoomAppointmentExample example =new TConferenceRoomAppointmentExample();
        TConferenceRoomAppointmentExample.Criteria criteria= example.createCriteria();
        criteria.andIsdelEqualTo(true);
        criteria.andConferenceRoomIdEqualTo(roomId);
        criteria.andExamineStatusNotEqualTo(3);
        List<TConferenceRoomAppointment> list=tConferenceRoomAppointmentMapper.selectByExample(example);
        if(list.size() !=0){
            return 1;
        }else{
            tConferenceRoomMapper.updateByPrimaryKeySelective(room);
            return 0;
        }

    }

    /**
     *空闲会议室查询：
//     * 1.先根据date，查出ref_conference中的会议信息
//     * 2.对开始、结束时间做逻辑判断
     notin
     select * from ref_conference
     where  date = '2020-05-07' and (starttime_code between 3 and 8) and (endtime_code between 3 and 8)
     --  (>=2 or endtime_code<=6)
     */
    /**
     * 判断时间的通用方法  lst2020/7/11
     *  * 开始时间~结束时间9~12：时长3h
     *  * 则9、10、11，不能进行预约。8只能预约1h的
     */
    @Override
    public List<FreeModel> freeRoomShow(String date){
//        int startcode =dicTimeMapper.selectBydescription(starttime);
//        int endcode = dicTimeMapper.selectBydescription(endtime);
//        String time=starttime+"--"+endtime;
        List<TConferenceRoom>  list= tConferenceRoomMapper.selectNotIn(date);
        List<FreeModel> listmodel = new ArrayList<FreeModel>();
        for(int i=0;i<list.size();i++){
            FreeModel model = new FreeModel();
            model.setRoomId(list.get(i).getId());
            model.setRoomName(list.get(i).getName());
            model.setAddress(list.get(i).getAddress());
            model.setCapacity(list.get(i).getCapacity());
            model.setStatus(list.get(i).getStatus());
            model.setEquipment(list.get(i).getEquipment());
            //会议室type
            int code = list.get(i).getConferenceRoomTypeCode();
            DicConferenceRoomType  roomType= dicConferenceRoomTypeMapper.selectByCode(code);
            model.setType(roomType.getDescription());
            listmodel.add(model);
        }
        if(listmodel != null && listmodel.size() > 0) {
            return listmodel;
        }
        else {
            return null;
        }
    }
    @Override
    public List<FreeModel> freeRoomShow2(String date){
//        int startcode =dicTimeMapper.selectBydescription(starttime);
//        int endcode = dicTimeMapper.selectBydescription(endtime);
//        String time=starttime+"--"+endtime;
           List<FreeModel> listmodel = new ArrayList<FreeModel>();
        List<RefConference> list2 =refConferenceMapper.selectByDate(date);
        for(int i=0;i<list2.size();i++){
            FreeModel model = new FreeModel();
            model.setConfenenceId(list2.get(i).getId());
            TConferenceRoom t= tConferenceRoomMapper.selectByPrimaryKey(list2.get(i).getConferenceRoomId());
            model.setConRoomName(t.getName());
            DicConferenceRoomType  roomType= dicConferenceRoomTypeMapper.selectByCode(t.getConferenceRoomTypeCode());
            model.setConRoomType(roomType.getDescription());
            model.setTime(list2.get(i).getTime());
            listmodel.add(model);
        }
        if(listmodel != null && listmodel.size() > 0) {
            return listmodel;
        }
        else {
            return null;
        }



    }




}
