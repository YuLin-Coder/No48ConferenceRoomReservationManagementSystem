package com.hbu.utils;

import com.hbu.dao.DicTimeMapper;
import com.hbu.dao.RefConferenceMapper;
import com.hbu.dao.TConferenceRoomAppointmentMapper;
import com.hbu.entity.RefConference;
import com.hbu.models.AppointmentModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * 判断时间的通用方法  lst2020/7/11
 * 开始时间~结束时间9~12：时长3h
 * 则9、10、11，不能进行预约。8只能预约1h的
 */
public class TimeHelper{

    public static int timeJudge(int start,int end,AppointmentModel model) {
        /**
         *  lst  时间 核心代码
         * 先去ref_conference表判断有没有预约重的会议室
         * 只有两种情况可以进行预约：8--start之间  和  end----18，两个时间段。。nu和num用来表示是否可以预约。
         * num：0表示不能预约
         * nu：0表示可以预约...（最终判断）
         */
        int num = 0;//0说明可以预约
//        int start = list.get(i).getStarttimeCode();
//        int end = list.get(i).getEndtimeCode();
        int maxhours;//最大时长
        //8---start
        for (int a = 8; a < start; a++) {
            //可预约的时间段为x---y
            int x = a;
            int y;
            maxhours = start - a;
            for (int k = 1; k <= maxhours; k++) {
                y = x + k;
                if (model.getStarttimeCode() == x && model.getEndtimeCode() == y) {
                    //输入的时间是可预约的时间，则说明没有冲突的会议
                    System.out.println("$$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@这个时间段没有会议");
                    num += 1;
                } else {
                    System.out.println("$$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@不能预约");
                }
            }
        }
        //end---18
        for (int j = 17; j >= end; j--) {
            //可预约的时间段为x---y
            int x = j;
            int y;
            maxhours = 18 - j;
            for (int m = 1; m <= maxhours; m++) {
                y = x + m;
                if (model.getStarttimeCode() == x && model.getEndtimeCode() == y) {
                    //输入的时间是可预约的时间，则说明没有冲突的会议
                    System.out.println("$$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@这个时间段没有会议");
                    num += 1;
                } else {
                    System.out.println("$$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@不能预约");
                }
            }
        }
        return num;
    }

    /**
     * 判断endtime与当前时间的关系
     * 预约时进行判断，最晚提前一天进行预约
     */
//    public static int currentTimeJudge(){
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//
//    }
}
