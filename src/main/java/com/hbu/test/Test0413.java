package com.hbu.test;

import java.util.List;

import com.hbu.utils.RedisUtil;
/**
 * redis练习
 * @author lst
 *
 */
public class Test0413 {
	public static void main(String[] args) {
		//最基本的String类型的get、set
		RedisUtil.set("lst", "0413 redis");
		System.out.println("redis||set、get字符串类型："+RedisUtil.get("lst"));
		
		//mset批量设置多个value对  //mget批量获取多个value对
		RedisUtil.mset(new String[]{"key1","12","key2","14","key3","16"});
		List<String> msetList=RedisUtil.mget(new String[]{"key1","key2","key3"});
		for(String i:msetList) {
			System.out.println(i);
		}
		
		//批量的设置key:value,可以一个,如果key已经存在则会失败,操作会回滚。失败返回0，成功为1
		RedisUtil.msetnx(new String[]{"key1","value1","key4","3546"});
		System.out.println(RedisUtil.msetnx(new String[]{"key1","value1","key4","3546"}));
		
		//单次删除一个key时可以使用 但是批量操作时有一些问题，可以使用mset方法批量覆盖掉之前舍弃掉的key value对
		//RedisUtil.del(token);
		
        //通过key向指定的value值追加值 返回值为追加后value的长度
		String token = "1234";
	    RedisUtil.append(token,"222");

	}
}
