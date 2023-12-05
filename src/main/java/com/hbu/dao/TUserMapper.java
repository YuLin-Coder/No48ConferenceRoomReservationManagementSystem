package com.hbu.dao;


import com.hbu.entity.TUser;
import com.hbu.entity.TUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {
    int countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    List<TUser> selectAll();//查全部

    TUser selectAllByNumber(@Param("number") long number);//通过教职工号查所有，教职工号是唯一标识

    List<TUser> selectByDepartment(@Param("department") String department); //根据所在部门查所有

    String selectDepartment(@Param("number") long number);//通过工号查找所在部门

    TUser selectByUUid(@Param("uuid") String uuid);//通过uuid查找到工号和姓名等

    String selectByNumber(@Param("number") long number);  //通过工号查出密码pw

    String selectMailByNumber(@Param("number") long number);  //通过工号查出邮箱
}