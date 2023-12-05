package com.hbu.dao;

import com.hbu.entity.UserInfo;
import com.hbu.entity.UserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * mybatis启动过程中会自动生成实现类  这个文件下所有的接口类都在xml文件中配置过了
 *  通过spring-dao.xml里的<property name="basePackage" value="com.hbu.dao" />
 *  这个叫做动态代理  cglib字节码注入生成相应的实现类  
 *  这个是aop的核心 除了这里还有log4j也是动态代理
 * @author 杨建兴
 *2020-03-26
 */
public interface UserInfoMapper {
    int countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);
     //这个是登录的第四步 --杨建兴
    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
	
	//二组获取个人信息
    UserInfo selectById(String uid);
}