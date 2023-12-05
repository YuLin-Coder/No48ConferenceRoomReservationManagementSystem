package com.hbu.dao;

import com.hbu.entity.TConferenceRoom;
import com.hbu.entity.TConferenceRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TConferenceRoomMapper {
    int countByExample(TConferenceRoomExample example);

    int deleteByExample(TConferenceRoomExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TConferenceRoom record);

    int insertSelective(TConferenceRoom record);

    List<TConferenceRoom> selectByExample(TConferenceRoomExample example);

    TConferenceRoom selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TConferenceRoom record, @Param("example") TConferenceRoomExample example);

    int updateByExample(@Param("record") TConferenceRoom record, @Param("example") TConferenceRoomExample example);

    int updateByPrimaryKeySelective(TConferenceRoom record);

    int updateByPrimaryKey(TConferenceRoom record);

    int count();//查询会议室总数

    int countMaintainSum();//查询会议室处于维修状态的总数

    //直接根据date和time查空闲会议室
    List<TConferenceRoom> selectNotIn( @Param("date") String date);

    List<TConferenceRoom> selectByDepartment(@Param("conferenceRoomTypeCode") int departmentcode); //根据所在部门查所有
}