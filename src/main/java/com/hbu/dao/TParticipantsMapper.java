package com.hbu.dao;

import com.hbu.entity.TParticipants;
import com.hbu.entity.TParticipantsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TParticipantsMapper {
    int countByExample(TParticipantsExample example);

    int deleteByExample(TParticipantsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TParticipants record);

    int insertSelective(TParticipants record);

    List<TParticipants> selectByExample(TParticipantsExample example);

    TParticipants selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TParticipants record, @Param("example") TParticipantsExample example);

    int updateByExample(@Param("record") TParticipants record, @Param("example") TParticipantsExample example);

    int updateByPrimaryKeySelective(TParticipants record);

    int updateByPrimaryKey(TParticipants record);
}