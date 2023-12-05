package com.hbu.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbu.dao.*;
import com.hbu.entity.*;
import com.hbu.models.AppointmentModel;
import com.hbu.models.AppointsModel;
import com.hbu.models.IconModel;
import com.hbu.models.OwnAppointModel;
import com.hbu.service.ConferenceService;
import com.hbu.utils.SendMail;
import com.hbu.utils.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    private TConferenceRoomMapper tConferenceRoomMapper;
    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private TConferenceRoomAppointmentMapper tConferenceRoomAppointmentMapper;
    @Autowired
    private RefConferenceMapper refConferenceMapper;
    @Autowired
    private DicTimeMapper dicTimeMapper;

    @Override
    public IconModel iconshow(){
        IconModel model = new IconModel();
        int roomSum = tConferenceRoomMapper.count();
        int appointedSum = refConferenceMapper.count();//已预约总数
        int maintainSum = tConferenceRoomMapper.countMaintainSum();//维修总数
       //一天分为10段。 空闲总数（总70- 已预约 - 维修*10）
        //有问题！！！！！！！！！！freesum  int freeSum = 70 - (maintainSum *10) - appointedSum;
        /**
         * @Author: 刘思彤
         * @Date: 2020/8/8
         * @Description: 本周可预约改为今日已预约
         **/
        RefConferenceExample ex = new RefConferenceExample();
        RefConferenceExample.Criteria criteria = ex.createCriteria();
        criteria.andIsdelEqualTo(true);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        criteria.andDateEqualTo(df.format(new Date()));
        int sumToday = refConferenceMapper.countByExample(ex);
         model.setAppointedSum(appointedSum);
         model.setSumToday(sumToday);
         model.setRoomSum(roomSum);
         model.setMaintainSum(maintainSum);
        if(model != null ) {
            return model;
        }
        else {
            return null;
        }
    }

    /**
     * 本系预约 ，插入预约表中状态为1，同时直接插入到会议表
     * model目前没有判断容量，，07/13
     * @return nu：0表示可以预约...（最终判断）
     */
    @Override
    public int ownerinsert(AppointmentModel model){
        TConferenceRoomAppointment t = new TConferenceRoomAppointment();
        t.setTheme(model.getTheme());
        t.setDate(model.getDate());
        t.setConferenceRoomId(model.getRoomId());
        t.setMaxNum(model.getNum());
        t.setNumber(model.getAppointerNumber());
        TUser t2=tUserMapper.selectAllByNumber(model.getAppointerNumber());
        t.setUsername(t2.getName());
        String starttime = model.getStarttime();
        String endtime = model.getEndtime();
        int startcode = dicTimeMapper.selectBydescription(starttime);
        int endcode = dicTimeMapper.selectBydescription(endtime);
        t.setStarttimeCode(startcode);
        t.setEndtimeCode(endcode);
        String time = starttime+"--"+endtime;
        t.setTime(time);
        t.setExamineStatus(1);
        t.setIsdel(true);
        /**
         *  lst  时间 核心代码
         * 先去ref_conference表判断有没有预约重的会议室
         * 只有两种情况可以进行预约：8--start之间  和  end----18，两个时间段。。nu和num用来表示是否可以预约。
         * num：0表示不能预约
         * nu：0表示可以预约
         */
        List<RefConference> list=refConferenceMapper.selectByDateRoomId(model.getDate(),model.getRoomId());
        if(list.size() !=0){
            int nu = 0;
            //在日期和会议室id的条件下有会议，判断会议时间
            for(int i=0;i<list.size();i++){
                int num =0;//0说明可以预约
                int start = list.get(i).getStarttimeCode();
                int end = list.get(i).getEndtimeCode();
                int maxhours ;//最大时长
                //8---start
                for(int a=8;a<start;a++){
                    //可预约的时间段为x---y
                    int x = a;
                    int y;
                    maxhours = start - a ;
                    for(int k=1;k<=maxhours;k++){
                        y = x + k;
                        if(model.getStarttimeCode() ==x && model.getEndtimeCode() ==y){
                            //输入的时间是可预约的时间，则说明没有冲突的会议
                            System.out.println("$$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@这个时间段没有会议");
                            num += 1;
                        }else{
                            System.out.println("$$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@不能预约");
                        }
                    }
                }
                //end---18
                for(int j=17;j>=end;j--){
                    //可预约的时间段为x---y
                    int x = j ;
                    int y;
                    maxhours = 18-j;
                    for(int m=1;m<=maxhours;m++){
                        y = x + m;
                        if(model.getStarttimeCode() ==x && model.getEndtimeCode() ==y){
                            //输入的时间是可预约的时间，则说明没有冲突的会议
                            System.out.println("$$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@这个时间段没有会议");
                            num +=  1;
                        }else{
                            System.out.println("$$$$$$$$$$$$$$$$$$$@@@@@@@@@@@@@@@@@不能预约");
                        }
                    }
                }
                if(num ==0){
                    nu ++;
                }
            }
            if(nu ==0){
                tConferenceRoomAppointmentMapper.insert(t);
                RefConference re= new RefConference();
                re.setDate(model.getDate());
                re.setTime(time);
                re.setConferenceRoomId(model.getRoomId());
                re.setConferenceRoomAppointmentId(t.getId());
                re.setStarttimeCode(startcode);
                re.setEndtimeCode(endcode);
                re.setIsdel(true);
                refConferenceMapper.insert(re);
                TUser user = tUserMapper.selectAllByNumber(model.getAppointerNumber());
                String pass ="恭喜您预约的日期为"+model.getDate()+" ,时间为 "+time+"的会议预约成功了，祝您会议顺利。";
                String receiver = user.getMailbox();
                String messagecontent  = pass;
                SendMail.sendMessage( receiver,messagecontent);
            }
           return  nu;
        }else{
            tConferenceRoomAppointmentMapper.insert(t);
            RefConference re= new RefConference();
            re.setDate(model.getDate());
            re.setTime(time);
            re.setConferenceRoomId(model.getRoomId());
            re.setConferenceRoomAppointmentId(t.getId());
            re.setStarttimeCode(startcode);
            re.setEndtimeCode(endcode);
            re.setIsdel(true);
            refConferenceMapper.insert(re);
            TUser user = tUserMapper.selectAllByNumber(model.getAppointerNumber());
            String pass ="恭喜您预约的日期为"+model.getDate()+" ,时间为 "+time+"的会议预约成功了，祝您会议顺利。";
            String receiver = user.getMailbox();
            String messagecontent  = pass;
            SendMail.sendMessage( receiver,messagecontent);
            return 0;
        }

    }
    /**
     * 跨系预约 ，到预约表，状态为0，由管理员进行审核。
     *
     * 只判断了会议表。。目前会发生多个人重复预约同一个时间，或者一个人多次预约同一个时间。
     * 全部由管理员进行审核
     * @param model
     * @return  nu：0表示可以预约...（最终判断）
     */
    @Override
    public int otherinsert(AppointmentModel model){
        TConferenceRoomAppointment t = new TConferenceRoomAppointment();
        t.setTheme(model.getTheme());
        t.setDate(model.getDate());
        t.setConferenceRoomId(model.getRoomId());
        t.setMaxNum(model.getNum());
        t.setNumber(model.getAppointerNumber());
        TUser t2=tUserMapper.selectAllByNumber(model.getAppointerNumber());
        t.setUsername(t2.getName());
        String starttime = model.getStarttime();
        String endtime = model.getEndtime();
        int startcode = dicTimeMapper.selectBydescription(starttime);
        int endcode = dicTimeMapper.selectBydescription(endtime);
        t.setStarttimeCode(startcode);
        t.setEndtimeCode(endcode);
        String time = starttime+"--"+endtime;
        t.setTime(time);
        t.setExamineStatus(0);
        t.setIsdel(true);
        t.setSpecialneeds(model.getSpecialneeds());
        /**
         *  lst  时间 核心代码
         *  * num：0表示不能预约
         *  @return  nu：0表示可以预约
         */
        List<RefConference> list=refConferenceMapper.selectByDateRoomId(model.getDate(),model.getRoomId());
       // List<TConferenceRoomAppointment> list2 =tConferenceRoomAppointmentMapper.selectByDateRoomId(model.getDate(),model.getRoomId());
        if(list.size() !=0){
            int nu = 0;
            //在日期和会议室id的条件下有会议，判断会议时间
            for(int i=0;i<list.size();i++){
               int num = TimeHelper.timeJudge(list.get(i).getStarttimeCode(),list.get(i).getEndtimeCode(),model);
                if(num ==0){
                    nu ++;
                }
            }
            if(nu ==0){
                tConferenceRoomAppointmentMapper.insert(t);
            }
            return  nu;
        }else{
            tConferenceRoomAppointmentMapper.insert(t);
            return 0;
        }

    }

    //我的预约显示
    @Override
    public PageInfo<OwnAppointModel> myAppointShow(long number, int page , int size){
        // 页码、每页的大小
        //startPage(1,10)第一个数表示第几页，第二个数表示每页有多少个
        PageHelper.startPage(page, size,true);
        List<OwnAppointModel> list =tConferenceRoomAppointmentMapper.selectByNumber2(number);
        for(int i=0;i<list.size();i++){
            long roomId = list.get(i).getConferenceRoomId();
            TConferenceRoom t= tConferenceRoomMapper.selectByPrimaryKey(roomId);
            list.get(i).setConferenceRoomName(t.getName());
        }
        PageInfo<OwnAppointModel> pageInfo = new PageInfo<OwnAppointModel>(list);
        if(list != null && list.size() > 0) {
            return pageInfo;
        }
        else {
            return null;
        }
    }

    //我的会议显示  不对-------------
    /*@Override
    public List<AppointmentModel> myConferenceShow(long number){
        List<OwnAppointModel> list =tConferenceRoomAppointmentMapper.selectByNumber2(number);
        List<AppointmentModel> listmodel = new ArrayList();
//        for(int i=0;i<list.size();i++){
//            AppointmentModel model =new AppointmentModel();
////            model.setRoomAdress(tConferenceRoomMapper.selectByPrimaryKey(listmodel.get(i).getRoomId()).getName());
//            model.setAppointId(list.get(i).getId());
//            model.setAppointerNumber(list.get(i).getNumber());
//            model.setAppointerName(list.get(i).getUsername());
//            model.setTheme(list.get(i).getTheme());
//            model.setRoomAdress(tConferenceRoomMapper.selectByPrimaryKey(list.get(i).getConferenceRoomId()).getName());
//            model.setDate(list.get(i).getDate());
//            int startcode = list.get(i).getStarttimeCode();
//            int endcode = list.get(i).getEndtimeCode();
//            String starttime=dicTimeMapper.selectByCode(startcode);
//            String endtime=dicTimeMapper.selectByCode(endcode);
//            String time = starttime+"--"+endtime;
//            model.setTime(time);
//            model.setSpecialneeds(list.get(i).getSpecialneeds());
//            listmodel.add(model);
//        }
        if(listmodel != null && listmodel.size() > 0) {
            return listmodel;
        }
        else {
            return null;
        }
    }*/

    @Override
    public int appointDel( int id){
        TConferenceRoomAppointmentExample example= new TConferenceRoomAppointmentExample();
        TConferenceRoomAppointmentExample.Criteria criteria =example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andIsdelEqualTo(true);
        List<TConferenceRoomAppointment> list =  tConferenceRoomAppointmentMapper.selectByExample(example);
        if(list.get(0).getExamineStatus() == 1 ){
            //同时删除会议表和预约表的数据
            RefConference re = new RefConference();
            re.setIsdel(false);
            RefConferenceExample ex2= new RefConferenceExample();
            RefConferenceExample.Criteria  criteria2 = ex2.createCriteria();
            criteria2.andConferenceRoomAppointmentIdEqualTo(id);
            refConferenceMapper.updateByExampleSelective(re,ex2);
            TConferenceRoomAppointment t2 =new TConferenceRoomAppointment();
            t2.setIsdel(false);
            t2.setId(id);
            tConferenceRoomAppointmentMapper.updateByPrimaryKeySelective(t2);
            return 0;
        }else{
            TConferenceRoomAppointment t3 =new TConferenceRoomAppointment();
            t3.setIsdel(false);
            t3.setId(id);
            tConferenceRoomAppointmentMapper.updateByPrimaryKeySelective(t3);
            return 0;
        }
    }

    //会议预约总情况
    @Override
    public PageInfo<TConferenceRoomAppointment> appoints(int page, int size){
        // 页码、每页的大小
        PageHelper.startPage(page, size,true);
        TConferenceRoomAppointmentExample  ex =new TConferenceRoomAppointmentExample();
        TConferenceRoomAppointmentExample.Criteria criteria = ex.createCriteria();
        criteria.andIsdelEqualTo(true);
        ex.setOrderByClause("id desc");
        List<TConferenceRoomAppointment> list =tConferenceRoomAppointmentMapper.selectByExample(ex);
        for(int i=0;i<list.size();i++){
            AppointsModel model =new AppointsModel();
            long roomId = list.get(i).getConferenceRoomId();
            TConferenceRoom t= tConferenceRoomMapper.selectByPrimaryKey(roomId);
            list.get(i).setConferenceRoomName(t.getName());
        }
        PageInfo<TConferenceRoomAppointment> pageInfo = new PageInfo<TConferenceRoomAppointment>(list);
        if(list != null && list.size() > 0) {
            return pageInfo;
        }
        else {
            return null;
        }
    }
}
