package com.hbu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbu.dao.TConferenceRoomAppointmentMapper;
import com.hbu.dao.TUserMapper;
import com.hbu.entity.TConferenceRoomAppointment;
import com.hbu.entity.TConferenceRoomAppointmentExample;
import com.hbu.entity.TUser;
import com.hbu.entity.TUserExample;
import com.hbu.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private TConferenceRoomAppointmentMapper tConferenceRoomAppointmentMapper;

    @Override
    public PageInfo<TUser> show(int page){
        PageHelper.startPage(page,10,true);
        List<TUser> list = tUserMapper.selectAll();
        PageInfo<TUser> pageInfo = new PageInfo<>(list);
        if(list != null && list.size() > 0) {
            return pageInfo;
        }
        else {
            return null;
        }
    }
    @Override
    public int insert(TUser t){
        TUser user = new TUser();
        user.setNumber(t.getNumber());
        user.setPassword(t.getPassword());
        user.setName(t.getName());
        user.setMailbox(t.getMailbox());
        user.setPhone(t.getPhone());
        user.setDepartment(t.getDepartment());
        user.setIsdel(true);
        user.setStatus((short)1);
        int num = tUserMapper.insert(user);
        //不成功返回0
        if(num == 0) {
            return 0;
        }else {
            return num;
        }
    }

    @Override
    public int update(TUser t){
    	TUser user = new TUser();
    	user.setId(t.getId());
        user.setNumber(t.getNumber());
        user.setPassword(t.getPassword());
        user.setName(t.getName());
        user.setMailbox(t.getMailbox());
        user.setPhone(t.getPhone());
        user.setDepartment(t.getDepartment());
        int num = tUserMapper.updateByPrimaryKeySelective(user);
        //不成功返回0
        if(num == 0) {
            return 0;
        }else {
            return num;
        }
    }
    @Override
    public int del(int id){
        TUser user = new TUser();
        user.setId(id);
        user.setIsdel(false);
        TUser t = tUserMapper.selectByPrimaryKey(id);
        Long number = t.getNumber();
        /**
         * 判断预约表，只要有相关会议就不能删
         */
        TConferenceRoomAppointmentExample example =new TConferenceRoomAppointmentExample();
        TConferenceRoomAppointmentExample.Criteria criteria= example.createCriteria();
        criteria.andIsdelEqualTo(true);
        criteria.andNumberEqualTo(number);
        criteria.andExamineStatusNotEqualTo(3);
        List<TConferenceRoomAppointment> list=tConferenceRoomAppointmentMapper.selectByExample(example);
        if(list.size() !=0){
            return 1;
        }else{
            tUserMapper.updateByPrimaryKeySelective(user);
            return 0;
        }
    }

    @Override
    public List<TUser> newuser(){
        TUserExample example =new TUserExample();
        TUserExample.Criteria criteria= example.createCriteria();
        criteria.andIsdelEqualTo(true);
        criteria.andStatusEqualTo((short) 0);
        List<TUser> list = tUserMapper.selectByExample(example);

        if(list != null && list.size() > 0) {
            return list;
        }
        else {
            return null;
        }
    }

}
