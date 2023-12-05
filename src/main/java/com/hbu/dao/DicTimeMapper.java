package com.hbu.dao;

import com.hbu.entity.DicTime;
import com.hbu.entity.DicTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DicTimeMapper {
    int countByExample(DicTimeExample example);

    int deleteByExample(DicTimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DicTime record);

    int insertSelective(DicTime record);

    List<DicTime> selectByExample(DicTimeExample example);

    DicTime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DicTime record, @Param("example") DicTimeExample example);

    int updateByExample(@Param("record") DicTime record, @Param("example") DicTimeExample example);

    int updateByPrimaryKeySelective(DicTime record);

    int updateByPrimaryKey(DicTime record);

    int selectBydescription(@Param("description") String  description);//根据描述查code

    String selectByCode(@Param("code") int  code);//根据code查描述
}