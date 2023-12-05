package com.hbu.dao;

import com.hbu.entity.TConferenceRoomAppointment;
import com.hbu.entity.TConferenceRoomAppointmentExample;
import java.util.List;

import com.hbu.models.AppointmentModel;
import com.hbu.models.OwnAppointModel;
import org.apache.ibatis.annotations.Param;

public interface TConferenceRoomAppointmentMapper {
    int countByExample(TConferenceRoomAppointmentExample example);

    int deleteByExample(TConferenceRoomAppointmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TConferenceRoomAppointment record);

    int insertSelective(TConferenceRoomAppointment record);

    List<TConferenceRoomAppointment> selectByExampleWithBLOBs(TConferenceRoomAppointmentExample example);

    List<TConferenceRoomAppointment> selectByExample(TConferenceRoomAppointmentExample example);

    TConferenceRoomAppointment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TConferenceRoomAppointment record, @Param("example") TConferenceRoomAppointmentExample example);

    int updateByExampleWithBLOBs(@Param("record") TConferenceRoomAppointment record, @Param("example") TConferenceRoomAppointmentExample example);

    int updateByExample(@Param("record") TConferenceRoomAppointment record, @Param("example") TConferenceRoomAppointmentExample example);

    int updateByPrimaryKeySelective(TConferenceRoomAppointment record);

    int updateByPrimaryKeyWithBLOBs(TConferenceRoomAppointment record);

    int updateByPrimaryKey(TConferenceRoomAppointment record);

    List<TConferenceRoomAppointment> selectAll();//预约申请，由管理员审核， 条件：examine_status = 0;
    List<TConferenceRoomAppointment> selectAll2();//预约申请，由系主任审核
    List<TConferenceRoomAppointment> showNoPass();//条件：examine_status = 3;

    int updateByPrimaryKey1(@Param("id") int id);//管理员：更新审核状态为1
    int nopass1(@Param("id") int id,@Param("examineReason") String examineReason);//管理员：不通过


    int updateByPrimaryKey2(@Param("id") int id);//系主任通过：更新审核状态为2

    List<AppointmentModel> selectByNumber(@Param("number") long number);//通过教职工号查所有

    List<OwnAppointModel> selectByNumber2(@Param("number") long number);//通过教职工号查所有

    List<TConferenceRoomAppointment> selectByDateRoomId(@Param("date") String date,@Param("conferenceRoomId") long conferenceRoomId);
}