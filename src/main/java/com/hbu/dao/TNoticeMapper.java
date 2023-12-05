package com.hbu.dao;

import com.hbu.entity.TNotice;
import com.hbu.entity.TNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TNoticeMapper {
    int countByExample(TNoticeExample example);

    int deleteByExample(TNoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TNotice record);

    int insertSelective(TNotice record);

    List<TNotice> selectByExampleWithBLOBs(TNoticeExample example);

    List<TNotice> selectByExample(TNoticeExample example);

    TNotice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TNotice record, @Param("example") TNoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") TNotice record, @Param("example") TNoticeExample example);

    int updateByExample(@Param("record") TNotice record, @Param("example") TNoticeExample example);

    int updateByPrimaryKeySelective(TNotice record);

    int updateByPrimaryKeyWithBLOBs(TNotice record);

    int updateByPrimaryKey(TNotice record);
}