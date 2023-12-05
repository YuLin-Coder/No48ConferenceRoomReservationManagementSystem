package com.hbu.dao;

import com.hbu.entity.DicConferenceRoomType;
import com.hbu.entity.DicConferenceRoomTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DicConferenceRoomTypeMapper {
    int countByExample(DicConferenceRoomTypeExample example);

    int deleteByExample(DicConferenceRoomTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DicConferenceRoomType record);

    int insertSelective(DicConferenceRoomType record);

    DicConferenceRoomType selectByCode(@Param("code") int code);//根据code查会议室类型描述和系主任名字

    int selectByDescription(@Param("description") String description);//通过类型描述查出code

    List<DicConferenceRoomType> selectByExample(DicConferenceRoomTypeExample example);

    DicConferenceRoomType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DicConferenceRoomType record, @Param("example") DicConferenceRoomTypeExample example);

    int updateByExample(@Param("record") DicConferenceRoomType record, @Param("example") DicConferenceRoomTypeExample example);

    int updateByPrimaryKeySelective(DicConferenceRoomType record);

    int updateByPrimaryKey(DicConferenceRoomType record);
}