package com.hbu.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	
	private static Properties props_en;
	private static Properties props_zh;
    static {
        loadProps();
    }

    synchronized static private void loadProps(){
        System.out.println("开始加载properties文件内容.......");
        props_en = new Properties();
        InputStream in = null;
        try {
            // 第一种，通过类加载器进行获取properties文件流-->
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("i18n/messages_en_US.properties");
            // 第二种，通过类进行获取properties文件流-->
            //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
            props_en.load(in);
        } catch (FileNotFoundException e) {
            System.out.println("jdbc.properties文件未找到");
        } catch (IOException e) {
            System.out.println("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                System.out.println("jdbc.properties文件流关闭出现异常");
            }
        }
        System.out.println("加载properties文件内容完成...........");
        System.out.println("properties文件内容：" + props_en);
    }
    
    synchronized static private void loadProps_zh(){
        System.out.println("开始加载properties文件内容.......");
        props_zh = new Properties();
        InputStream in = null;
        try {
            // 第一种，通过类加载器进行获取properties文件流-->
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("i18n/messages_zh_CN.properties");
            // 第二种，通过类进行获取properties文件流-->
            //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
            props_zh.load(in);
        } catch (FileNotFoundException e) {
            System.out.println("jdbc.properties文件未找到");
        } catch (IOException e) {
            System.out.println("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                System.out.println("jdbc.properties文件流关闭出现异常");
            }
        }
        System.out.println("加载properties文件内容完成...........");
        System.out.println("properties文件内容：" + props_zh);
    }

    /**
     * 根据key获取配置文件中的属性
     */
    public static String getProperty(String key){
        if(null == props_en) {
            loadProps();
        }
        return props_en.getProperty(key);
    }

    /**
     * 根据key获取配置文件中的属性，当为null时返回指定的默认值
     * lanType = 1表示英文
     * lanType = 2 表示汉语
     */
    public static String getProperty(String key, String defaultValue, int lanType) {
        
    	if( lanType == 1) {
    		
    		if(null == props_en) {
                loadProps();
            }
            
            
            return props_en.getProperty(key, defaultValue);
    	}else {
    		if(null == props_zh) {
                loadProps_zh();
            }
            
            
            return props_zh.getProperty(key, defaultValue);
    	}
    	
    	
    }
    
    
    public static void main(String[] asd) {
    	System.out.println(getProperty("header_missing_uid","===",2));
    }

}
