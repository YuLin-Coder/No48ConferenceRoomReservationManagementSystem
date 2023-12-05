package com.hbu.web;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import com.hbu.utils.RedisUtil;


public class BaseController {
	//获取未格式化的UUID
    public static String getId() {
        return UUID.randomUUID().toString();
    }
    /**
     * 调用样例
     * Result< List<PayModel>  >  result = new Result< List<PayModel>  >();
		
		if(!checklogin(request)) {
			result.setCode("999999");
			result.setMessage("请登录");
			return result;
		}
     * @param request
     * @return
     */
	public static boolean checklogin(HttpServletRequest request) {
		String token = request.getHeader("token");
		
		String redisutil = RedisUtil.get(token);
		if(redisutil==null) {
			return false;
		}else {
			return true;
		}	
	}
	
	public static String getUserId(HttpServletRequest request) {
		
		if(checklogin(request)) {
			String token = request.getHeader("token");
			return RedisUtil.get(token);
		}else {
			return null;
		}
		
	
	}

	/**
	 * 后台token2
	 */
	public static boolean checklogin2(HttpServletRequest request) {
		String token2 = request.getHeader("token2");

		String redisutil = RedisUtil.get(token2);
		if(redisutil==null) {
			return false;
		}else {
			return true;
		}
	}

	public static String getUserId2(HttpServletRequest request) {

		if(checklogin(request)) {
			String token = request.getHeader("token2");
			return RedisUtil.get(token);
		}else {
			return null;
		}


	}
	
}
