package com.hbu.dao;

import com.hbu.entity.TRules;
import com.hbu.entity.TRulesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRulesMapper {
    int countByExample(TRulesExample example);

    int deleteByExample(TRulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRules record);

    int insertSelective(TRules record);

    List<TRules> selectByExampleWithBLOBs(TRulesExample example);


    List<TRules> selectByExample(TRulesExample example);

    TRules selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRules record, @Param("example") TRulesExample example);

    int updateByExampleWithBLOBs(@Param("record") TRules record, @Param("example") TRulesExample example);

    int updateByExample(@Param("record") TRules record, @Param("example") TRulesExample example);

    int updateByPrimaryKeySelective(TRules record);

    int updateByPrimaryKeyWithBLOBs(TRules record);

    int updateByPrimaryKey(TRules record);
}