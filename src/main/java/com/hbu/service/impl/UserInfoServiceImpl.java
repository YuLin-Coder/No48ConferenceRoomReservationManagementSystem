package com.hbu.service.impl;

import java.util.List;

import com.hbu.dao.UserInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbu.entity.UserInfo;
import com.hbu.entity.UserInfoExample;
import com.hbu.entity.UserInfoExample.Criteria;
import com.hbu.service.UserInfoService;

//注意：这里一定要加service注解 --lst
@Service
public class UserInfoServiceImpl implements UserInfoService {

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 注入Service依赖
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Override
	public int insert(UserInfo record) {
		// TODO Auto-generated method stub
		return userInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(UserInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserInfo selectByPrimaryKey(String userId) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByPrimaryKey(userId);
	}

	//根据非ID参数进行查询  这里是第三步
	@Override
	public UserInfo selectByUserNameAndPassword(String userName, String userPassword) {
		// TODO Auto-generated method stub
		UserInfoExample userInfoExample = new UserInfoExample();
		Criteria criteria = userInfoExample.createCriteria();
		criteria.andUserNameEqualTo(userName);
		criteria.andUserPasswordEqualTo(userPassword);
		List<UserInfo> list = userInfoMapper.selectByExample(userInfoExample);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	
	
	
	//根据非ID参数进行查询
//		@Override
//		public UserInfo selectByUserNameAndPassword(String userName, String userPassword) {
//			// TODO Auto-generated method stub
//			UserInfoExample userInfoExample = new UserInfoExample();
//			Criteria criteria = userInfoExample.createCriteria();
//			criteria.andUserNameEqualTo(userName);
//			criteria.andUserPasswordEqualTo(userPassword);
//			List<UserInfo> list = userInfoMapper.selectByExample(userInfoExample);
//			if(list != null && list.size() > 0) {
//				return list.get(0);
//			}
//			else {
//				return null;
//			}
//		}

}
