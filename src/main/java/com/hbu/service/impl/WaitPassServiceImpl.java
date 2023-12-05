package com.hbu.service.impl;

import com.hbu.dao.*;
import com.hbu.entity.*;
import com.hbu.models.AppointmentModel;
import com.hbu.models.WaitPassModel;
import com.hbu.service.WaitPassService;

import com.hbu.utils.SendMail;
import com.hbu.utils.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WaitPassServiceImpl implements WaitPassService {
    @Autowired
    private TConferenceRoomAppointmentMapper tConferenceRoomAppointmentMapper;
    @Autowired
    private TConferenceRoomMapper tConferenceRoomMapper;
    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private DicConferenceRoomTypeMapper dicConferenceRoomTypeMapper;
    @Autowired
    private RefConferenceMapper refConferenceMapper;
    @Autowired
    private DicTimeMapper dicTimeMapper;


    @Override
    public List<WaitPassModel> showAll(){
        /**
         *1.在预约表中查出所有，审核状态为0的数据
         */
        List<TConferenceRoomAppointment> list=tConferenceRoomAppointmentMapper.selectAll();
        List<WaitPassModel> listmodel=new ArrayList<WaitPassModel>();
        if(list.size() == 0){
            return null;
        }else{
            for(int i=0;i<list.size();i++){
                WaitPassModel model=new WaitPassModel();
                model.setAppointmentId(list.get(i).getId());
                model.setUsername(list.get(i).getUsername());
                model.setNumber(list.get(i).getNumber());
                model.setDate(list.get(i).getDate());
                /**
                 * 2.setDepartment：需要根据number去t_user里查
                 * 3.根据conference_room_id:setRoomName\setRoomType需要去t_conference_room会议表等里查
                 * 4.dicConferenceRoomType：查会议室类别
                 *   ！！！！！需要加判断，如果预约表中对应的roomId、number被删掉了，会报错
                 *   目前写的是全部都不显示，应该是出问题的不显示，而正常的依旧显示
                 */
                try{
                    long number=list.get(i).getNumber();
                    model.setDepartment(tUserMapper.selectDepartment(number));
                    long roomId = list.get(i).getConferenceRoomId();
                    TConferenceRoom room = tConferenceRoomMapper.selectByPrimaryKey(roomId);
                    //  System.out.println("11111111111111111111111111111111111111111111111111111111111111111111");
                    model.setRoomName(room.getName());
                    int code=room.getConferenceRoomTypeCode();
                    DicConferenceRoomType dicConferenceRoomType= dicConferenceRoomTypeMapper.selectByCode(code);
                    model.setRoomType(dicConferenceRoomType.getDescription());
                    model.setTheme(list.get(i).getTheme());
                    model.setStarttimeCode(list.get(i).getStarttimeCode());
                    model.setEndtimeCode(list.get(i).getEndtimeCode());
                    int startcode = list.get(i).getStarttimeCode();
                    int endcode=list.get(i).getEndtimeCode();
                    String starttime=dicTimeMapper.selectByCode(startcode);
                    String endtime=dicTimeMapper.selectByCode(endcode);
                    String time = starttime+"--"+endtime;
                    model.setTime(time);
                    model.setSpecialneeds(list.get(i).getSpecialneeds());
                    model.setConferenceRoomId(list.get(i).getConferenceRoomId());
                    model.setMaxNum(list.get(i).getMaxNum());
                    model.setExamineStatus(list.get(i).getExamineStatus());
                    listmodel.add(model);
                }catch (Exception e){
                    listmodel = null;
                    System.out.println("发生异常，数据库数据错误");//最好在controller加上原因，弹窗弹出来
                }
            }
        }

        if(listmodel != null && listmodel.size() > 0) {
            return listmodel;
        }
        else {
            return null;
        }
    }
    
    @Override
    public List<WaitPassModel> showAll2(){
    	 List<TConferenceRoomAppointment> list=tConferenceRoomAppointmentMapper.selectAll2();
         System.out.println(list);
         List<WaitPassModel> listmodel=new ArrayList<WaitPassModel>();
         for(int i=0;i<list.size();i++){
             WaitPassModel model=new WaitPassModel();
             model.setAppointmentId(list.get(i).getId());
             model.setUsername(list.get(i).getUsername());
             model.setNumber(list.get(i).getNumber());
             model.setDate(list.get(i).getDate());
             /**
              * setDepartment：需要根据number去user里查
 		             * 根据conference_room_id:setRoomName\setRoomType需要去会议表等里查
 		             * 共涉及四个表
              */
             long number=list.get(i).getNumber();
             model.setDepartment(tUserMapper.selectDepartment(number));
             long roomId = list.get(i).getConferenceRoomId();
             TConferenceRoom room = tConferenceRoomMapper.selectByPrimaryKey(roomId);      
             //room.getName()这可能有问题，比如会议室表的id不存在，后台会说空指针            
             model.setRoomName(room.getName());
             int code=room.getConferenceRoomTypeCode();
             DicConferenceRoomType dicConferenceRoomType= dicConferenceRoomTypeMapper.selectByCode(code);
             model.setRoomType(dicConferenceRoomType.getDescription());
             model.setTheme(list.get(i).getTheme());
             model.setTime(list.get(i).getTime());
             model.setMaxNum(list.get(i).getMaxNum());
             model.setExamineStatus(list.get(i).getExamineStatus());
             listmodel.add(model);
         }
         if(listmodel != null && listmodel.size() > 0) {
             return listmodel;
         }
         else {
             return null;
         }
    }

    /**
     *   管理员审核 通过 （将审核状态.改成1，默认是0）,update status=1
     *   并存到ref_conference表（目前是一级审核）
     * @return   nu：0表示可以预约...（最终判断）,参考otherinsert()
     */
    @Override
    public int waitpass1(int appointmentId){
        TConferenceRoomAppointment t1 = tConferenceRoomAppointmentMapper.selectByPrimaryKey(appointmentId);
        AppointmentModel model = new AppointmentModel();
        model.setStarttimeCode(t1.getStarttimeCode());
        model.setEndtimeCode(t1.getEndtimeCode());
        /**
         *  lst  时间 核心代码 num:0 不能预约，nu：0可以预约
         */
        List<RefConference> list=refConferenceMapper.selectByDateRoomId(t1.getDate(),t1.getConferenceRoomId());
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
                /**
                 * 预约表通过id查找number，然后找邮箱..如果用户表里没有该教职工号，那么是无法预约的
                 */
                tConferenceRoomAppointmentMapper.updateByPrimaryKey1(appointmentId);
                RefConference re = new RefConference();
                re.setConferenceRoomAppointmentId(appointmentId);
                re.setConferenceRoomId(t1.getConferenceRoomId());
                re.setDate(t1.getDate());
                re.setStarttimeCode(t1.getStarttimeCode());
                re.setEndtimeCode(t1.getEndtimeCode());
                String starttime=dicTimeMapper.selectByCode(t1.getStarttimeCode());
                String endtime=dicTimeMapper.selectByCode(t1.getEndtimeCode());
                String time=starttime+"--"+endtime;
                re.setTime(time);
                re.setIsdel(true);
                refConferenceMapper.insert(re);
                long number = t1.getNumber();
                TUser user = tUserMapper.selectAllByNumber(number);
                String pass ="恭喜您预约的日期为"+t1.getDate()+" ,时间为 "+time+"的会议预约成功了，祝您会议顺利。";
                String receiver = user.getMailbox();
                String messagecontent  = pass;
                SendMail.sendMessage( receiver,messagecontent);
                return nu;
            }else {
                //审核通过但是已经有会议了，直接变成不通过
                String starttime=dicTimeMapper.selectByCode(t1.getStarttimeCode());
                String endtime=dicTimeMapper.selectByCode(t1.getEndtimeCode());
                String time=starttime+"--"+endtime;
                String reason ="该时间段已经被预约,请重新预约";
                tConferenceRoomAppointmentMapper.nopass1(appointmentId,reason);
                long number = t1.getNumber();
                TUser user = tUserMapper.selectAllByNumber(number);
                String receiver = user.getMailbox();
                String nopass = "对不起，您预约的日期为"+t1.getDate()+"  , 时间为"+time+"的会议，预约失败了。不通过理由是： ";
                String messagecontent  = nopass + reason;
                SendMail.sendMessage( receiver,messagecontent);
                return  nu;
            }
        }else{
            tConferenceRoomAppointmentMapper.updateByPrimaryKey1(appointmentId);
            RefConference re = new RefConference();
            re.setConferenceRoomAppointmentId(appointmentId);
            re.setConferenceRoomId(t1.getConferenceRoomId());
            re.setDate(t1.getDate());
            re.setStarttimeCode(t1.getStarttimeCode());
            re.setEndtimeCode(t1.getEndtimeCode());
            String starttime=dicTimeMapper.selectByCode(t1.getStarttimeCode());
            String endtime=dicTimeMapper.selectByCode(t1.getEndtimeCode());
            String time=starttime+"--"+endtime;
            re.setTime(time);
            re.setIsdel(true);
            refConferenceMapper.insert(re);
            long number = t1.getNumber();
            TUser user = tUserMapper.selectAllByNumber(number);
            String pass ="恭喜您预约的日期为"+t1.getDate()+" ,时间为 "+time+"的会议预约成功了，祝您会议顺利。";
            String receiver = user.getMailbox();
            String messagecontent  = pass;
            SendMail.sendMessage( receiver,messagecontent);
            return 0;
        }
    }


  //管理员审核 不通过  （将审核状态.改成3）,update status=3
    @Override
    public int waitnopass1(int id,String reason) {
    	int num = tConferenceRoomAppointmentMapper.nopass1(id, reason);
        /**
         * 预约表通过id查找number，然后找邮箱
         */
        TConferenceRoomAppointment tConferenceRoomAppointment = tConferenceRoomAppointmentMapper.selectByPrimaryKey(id);
        long number = tConferenceRoomAppointment.getNumber();
        TUser user = tUserMapper.selectAllByNumber(number);
        String starttime=dicTimeMapper.selectByCode(tConferenceRoomAppointment.getStarttimeCode());
        String endtime=dicTimeMapper.selectByCode(tConferenceRoomAppointment.getEndtimeCode());
        String time=starttime+"--"+endtime;
        String nopass = "对不起，您预约的日期为"+tConferenceRoomAppointment.getDate()+"  , 时间为"+time+"的会议，预约失败了。不通过理由是： ";
        String receiver = user.getMailbox();
        String messagecontent  = nopass + reason;
        SendMail.sendMessage( receiver,messagecontent);
        //不成功返回0
        if(num == 0) {
            return 0;
        }else {
            return num;
        }
    }

    //系主任审核 （将审核状态.改成2）-----弃用
    @Override
    public int waitpass2(int appointmentId){
        int num = tConferenceRoomAppointmentMapper.updateByPrimaryKey2(appointmentId);
        if(num == 0) {
            return 0;
        }else {
            return num;
        }
    }

    //显示未通过
    @Override
    public List<WaitPassModel> showNoPass(){
        List<TConferenceRoomAppointment> list=tConferenceRoomAppointmentMapper.showNoPass();
        List<WaitPassModel> listmodel=new ArrayList<WaitPassModel>();
        if(list.size() == 0){
            return null;
        }else{
            for(int i=0;i<list.size();i++){
                WaitPassModel model=new WaitPassModel();
                model.setAppointmentId(list.get(i).getId());
                model.setUsername(list.get(i).getUsername());
                model.setNumber(list.get(i).getNumber());
                model.setDate(list.get(i).getDate());
                try{
                    long number=list.get(i).getNumber();
                    model.setDepartment(tUserMapper.selectDepartment(number));
                    long roomId = list.get(i).getConferenceRoomId();
                    TConferenceRoom room = tConferenceRoomMapper.selectByPrimaryKey(roomId);
                    //  System.out.println("11111111111111111111111111111111111111111111111111111111111111111111");
                    model.setRoomName(room.getName());
                    int code=room.getConferenceRoomTypeCode();
                    DicConferenceRoomType dicConferenceRoomType= dicConferenceRoomTypeMapper.selectByCode(code);
                    model.setRoomType(dicConferenceRoomType.getDescription());
                    model.setTheme(list.get(i).getTheme());
                    model.setStarttimeCode(list.get(i).getStarttimeCode());
                    model.setEndtimeCode(list.get(i).getEndtimeCode());
                    int startcode = list.get(i).getStarttimeCode();
                    int endcode=list.get(i).getEndtimeCode();
                    String starttime=dicTimeMapper.selectByCode(startcode);
                    String endtime=dicTimeMapper.selectByCode(endcode);
                    String time = starttime+"--"+endtime;
                    model.setTime(time);
                    model.setMaxNum(list.get(i).getMaxNum());
                    model.setExamineStatus(list.get(i).getExamineStatus());
                    model.setExamineReason(list.get(i).getExamineReason());
                    listmodel.add(model);
                }catch (Exception e){
                    listmodel = null;
                    System.out.println("发生异常，数据库数据错误");//最好在controller加上原因，弹窗弹出来
                }
            }
        }

        if(listmodel != null && listmodel.size() > 0) {
            return listmodel;
        }
        else {
            return null;
        }
    }

    @Override
    public int userpass(int id){
        TUser user= new TUser();
        user.setId(id);
        user.setStatus((short) 1);
        if( tUserMapper.updateByPrimaryKeySelective(user) == 0) {
            return 0;
        }else {
            tUserMapper.updateByPrimaryKeySelective(user);
            TUser t = tUserMapper.selectByPrimaryKey(id);
            String pass ="恭喜您，用户申请成功，欢迎使用管理学院会议室预约系统";
            String receiver = t.getMailbox();
            String messagecontent  = pass;
            SendMail.sendMessage( receiver,messagecontent);
            return  1;
        }
    }

    @Override
    public int usernopass(int id,String reason){
        TUser user= new TUser();
        user.setId(id);
        user.setReason(reason);
        user.setStatus((short) 2);
        if( tUserMapper.updateByPrimaryKeySelective(user) == 0) {
            return 0;
        }else {
            tUserMapper.updateByPrimaryKeySelective(user);
            TUser t = tUserMapper.selectByPrimaryKey(id);
            //标题：河北大学管理学院会议室预约系统—用户申请结果
            String unpass ="很抱歉，您的用户申请审核失败了，请重新申请或者联系综合办公室管理员。";
            String receiver = t.getMailbox();
            String messagecontent =unpass;
            if(reason != "无"){
                messagecontent  = unpass +"不通过理由是： "+ reason;
            }

            SendMail.sendMessage( receiver,messagecontent);
            return  1;
        }
    }
}
