package com.hbu.web;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.hbu.dao.DicTimeMapper;
import com.hbu.entity.*;
import com.hbu.models.*;
import com.hbu.service.*;
import com.hbu.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 前台controller
 * 2020/5/3
 */
@Controller
@RequestMapping("/front")
public class FrontController extends BaseController{
    private static Gson gson = new Gson();//将对象转成json类型字符串的工具
    //注入service依赖
    @Autowired
    private LoginService loginService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserManageService userManageService;
    @Autowired
    private DicTimeMapper dicTimeMapper;

    //1.前台登录 front/login
    @RequestMapping(value = "userLogin", method = RequestMethod.GET)
    @ResponseBody
    private Result<String> login(@RequestParam("number") long number, @RequestParam("password") String password ) {
        System.out.println("$$$$$$ /front/login方法的入参为：number="+number+",password="+password);
        Result<String> result=new Result<String>();
        int tAdmin=loginService.frontlogin(number,password);
        if(tAdmin==-1) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else if (tAdmin==0){
            result.setCode("888888");
            result.setMessage("密码不正确");
        } else {
            result.setCode("000000");
            result.setMessage("登录成功");
            /**
             * 设置cookie，并存到redis中。
             * 但是redis的value值，目前是number值。。。。实际应该是uuid（将number随机也行）
             */
            String token=getId().replace("-", "");
            RedisUtil.set(token,String.valueOf(number));
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(token);
        }
        System.out.println("$$$$$$ /front/login方法的入参为：number="+number+",password="+password+"/front/login方法的返回参数："+gson.toJson(result));
        return result;
    }

    /**
     * 用户忘记密码,发送邮件接口
     */
    @RequestMapping(value = "userforgetpw", method = RequestMethod.GET)
    @ResponseBody
    private Result<String> forgetpw( @RequestParam("number") long number, @RequestParam("mailbox") String mailbox ) {
        System.out.println("$$$$$$ back/login2方法的入参为：number="+number+",mailbox="+mailbox);
        Result<String> result=new Result<String>();
        int tAdmin=loginService.userforgetpw(number,mailbox);

        if(tAdmin==-1) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else if (tAdmin==0){
            result.setCode("888888");
            result.setMessage("邮箱不正确");
        } else {
            result.setCode("000000");
            result.setMessage("发送成功");
        }
        System.out.println("$$$$$$ back/login2方法的入参为：number="+number+",mailbox="+mailbox+"back/login2方法的返回参数："+gson.toJson(result));
        return result;
    }
      /**
     * 用户注册
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    @ResponseBody
    private Result<String> register( @RequestParam("number") long number,@RequestParam("department")String department,@RequestParam("name")String name ,@RequestParam("mailbox") String mailbox ,@RequestParam("password")String password ) {
        Result<String> result=new Result<String>();
        int tAdmin=loginService.userregister(number,mailbox,password,name,department);

        if(tAdmin != 1) {
            result.setCode("999999");
            result.setMessage("用户已经存在");
        } else {
            result.setCode("000000");
            result.setMessage("注册成功");
        }
        return result;
    }

    /**
     * 2.公告信息展示：get、url：/front/noticeShow
     * ++++++(时间不行)
     */
    @RequestMapping(value = "noticeShow", method = RequestMethod.GET)
    @ResponseBody
    private Result<PageInfo<TNotice>> noticeShow(@RequestParam("page") int page) {
        Result<PageInfo<TNotice>> result = new Result<PageInfo<TNotice>>();
        PageInfo<TNotice> list=noticeService.show(page);//查出来的所有数据
        if(list == null) {
            result.setCode("888888");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        return  result;
    }
    /**
     *3.信息详细展示：get、url：/front/noticeShowDetails
     * ++++++(时间不行)
     */
    @RequestMapping(value = "noticeShowDetails", method = RequestMethod.GET)
    @ResponseBody
    private Result<TNotice> noticeShowDetails(@RequestParam("noticeId") int noticeId ) {
//        String userid = getUserId(request);
//        System.out.println("$$$$$$ /front/noticeShowDetails方法的入参为：调用者id:"+userid);
        Result<TNotice> result = new Result<TNotice>();
        //判断是否登录
//        if(!checklogin(request)) {
//            result.setCode("999999");
//            result.setMessage("请登录");
//            return result;
//        }
        TNotice t=noticeService.showdetails(noticeId);//查出来的所有数据
        
        if(t == null) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(t);
        }
        System.out.println("$$$$$$ /front/noticeShowDetails方法的入参为：调用者id:"+"/front/login2方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 4.图标显示：get、url：/front/iconShow
     * ++++++++++
     */
    @RequestMapping(value = "iconShow", method = RequestMethod.GET)
    @ResponseBody
    private Result<IconModel> iconShow() {
//        String userid = getUserId(request);
        System.out.println("$$$$$$ /front/iconShow方法的入参为：调用者id:");
        Result<IconModel> result = new Result<IconModel>();
        //判断是否登录
//        if(!checklogin(request)) {
//            result.setCode("999999");
//            result.setMessage("请登录");
//            return result;
//        }
        IconModel model = conferenceService.iconshow();

        if(model == null) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(model);
        }
        System.out.println("$$$$$$ /front/iconShow方法的入参为：调用者id:userid"+"/front/iconShow方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 5.条例显示  get  front/ruleShow,,,,,弃用！
     * +++++++
     */
    @RequestMapping(value = "ruleShow", method = RequestMethod.GET)
    @ResponseBody
    private Result<TRules> ruleShow() {
     //   String userid = getUserId(request);
        System.out.println("$$$$$$ /front/ruleShow方法的入参为：调用者id:");
        Result<TRules> result = new Result<TRules>();
        //判断是否登录
//        if(!checklogin(request)) {
//            result.setCode("999999");
//            result.setMessage("请登录");
//            return result;
//        }
        TRules rules = noticeService.ruleshow();

        if(rules == null) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(rules);
        }
        System.out.println("$$$$$$ /front/ruleShow方法的入参为：调用者id:"+"/front/ruleShow方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 6.会议室选择显示：get  front/roomShow  跨系
     * ++++++
     */
    @RequestMapping(value = "roomShow", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<RoomModel>> roomShow(HttpServletRequest request) {
       // String userid = getUserId(request);
        System.out.println("$$$$$$ /front/roomShow方法的入参为：调用者id:");
        Result<List<RoomModel>> result = new Result<List<RoomModel>>();
        //判断是否登录
        
        List<RoomModel> list=roomService.show();

        if(list == null ) {
            result.setCode("888888");
            result.setMessage("会议室均处于维修状态，暂无法预约");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        System.out.println("$$$$$$ /front/roomShow方法的入参为：调用者id:"+"/front/roomShow方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }

    /**
     * 本系预约显示，只显示本系的会议室
     * +++++++++++++++
     * @return
     */
    @RequestMapping(value = "roomShow1", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<RoomModel>> roomShow1(HttpServletRequest request) {
        System.out.println("$$$$$$ /front/roomShow方法的入参为：调用者id:request");
        Result<List<RoomModel>> result = new Result<List<RoomModel>>();
        //判断是否登录
        
        String usernum = getUserId(request);
        long usernumber=Long.parseLong(usernum);
        List<RoomModel> list=roomService.show1(usernumber);

        if(list == null ) {
            result.setCode("111111");
            result.setMessage("您所在的系别没有相关会议室，或者会议室均处于维修状态，请选择跨系预约");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        System.out.println("$$$$$$ /front/roomShow方法的入参为：调用者id:"+"/front/roomShow方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
   /* *//**
     *7.参会人员选择显示：get  front/participantsShow。。。调的不对
     *//*
    @RequestMapping(value = "participantsShow", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<TUser>> participantsShow() {
      //  String userid = getUserId(request);
        System.out.println("$$$$$$ front/participantsShow方法的入参为：调用者id:");
        Result<List<TUser>> result = new Result<List<TUser>>();
        //判断是否登录
//        if(!checklogin(request)) {
//            result.setCode("999999");
//            result.setMessage("请登录");
//            return result;
//        }
        List<TUser> list = userManageService.show();

        if(list == null) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        System.out.println("$$$$$$ front/participantsShow方法的入参为：调用者id:"+"front/participantsShow方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }*/
    /**
     * 8）本系预约  post、url：/front/ownerAppointment
     * ++++++++++
     */
    @RequestMapping(value = "ownerAppointment", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> ownerAppointment(@RequestParam("theme") String theme,@RequestParam("date") String date,@RequestParam("starttime") String starttime,@RequestParam("endtime") String endtime,@RequestParam("roomId") String roomId,@RequestParam("num") int num,HttpServletRequest request) {
        Result<String> result = new Result<String>();
        //判断是否登录
        
        String usernum = getUserId(request);
        long usernumber=Long.parseLong(usernum);
        System.out.println("$$$$$$ /front/ownerAppointment方法的入参为：调用者id:"+usernumber);
        AppointmentModel model = new AppointmentModel();
        model.setTheme(theme);
        model.setDate(date);
//        model.setStarttime(starttime);
//        model.setEndtime(endtime);
        int startcode = dicTimeMapper.selectBydescription(starttime);
        int endcode = dicTimeMapper.selectBydescription(endtime);
        model.setStarttimeCode(startcode);
        model.setEndtimeCode(endcode);
        model.setStarttime(starttime);
        model.setEndtime(endtime);
        //roomId.replace("roomId","");入参是roomId2
        long roomId2 =Long.parseLong(roomId.replace("roomId",""));
        model.setRoomId(roomId2);
        model.setNum(num);
        model.setAppointerNumber(usernumber);
        int nn = conferenceService.ownerinsert(model);
        if(nn != 0) {
            result.setCode("111111");
            result.setMessage("该时间段已经有会议了，请重新预约");
        }else {
            //nn=0
            result.setCode("000000");
            result.setMessage("预约成功");
        }
        System.out.println("$$$$$$ /front/ownerAppointment方法的入参为：调用者id:"+"/front/ownerAppointment方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 9）跨系预约   post、url：/front/otherAppointment
     * ----------------------
     */
    @RequestMapping(value = "otherAppointment", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> otherAppointment(@RequestParam("theme") String theme,@RequestParam("date") String date,@RequestParam("starttime") String starttime,@RequestParam("endtime") String endtime,@RequestParam("roomId") String roomId,@RequestParam("num") int num,@RequestParam("specialneeds") String specialneeds,HttpServletRequest request){
        Result<String> result = new Result<String>();
        //判断是否登录
        
        String usernum = getUserId(request);
        long usernumber=Long.parseLong(usernum);
        System.out.println("$$$$$$ /front/ownerAppointment方法的入参为：调用者id:"+usernumber);
        AppointmentModel model = new AppointmentModel();
        model.setTheme(theme);
        model.setDate(date);
//        model.setStarttime(starttime);
//        model.setEndtime(endtime);
        int startcode = dicTimeMapper.selectBydescription(starttime);
        int endcode = dicTimeMapper.selectBydescription(endtime);
        model.setStarttimeCode(startcode);
        model.setEndtimeCode(endcode);
        model.setStarttime(starttime);
        model.setEndtime(endtime);
        //roomId.replace("roomId","");入参是roomId2
        long roomId2 =Long.parseLong(roomId.replace("roomId",""));
        model.setRoomId(roomId2);
        model.setNum(num);
        model.setAppointerNumber(usernumber);
        model.setSpecialneeds(specialneeds);
        int nn = conferenceService.otherinsert(model);
        if(nn != 0) {
            result.setCode("111111");
            result.setMessage("该时间段已经有会议了，请重新预约");
        }else {
            //nn=0
            result.setCode("000000");
            result.setMessage("预约成功");
        }
        System.out.println("$$$$$$ /front/otherAppointment方法的入参为：调用者id:"+"/front/otherAppointment方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }

    /**
     *10）我的预约显示	get  front/myAppointShow
     */
    @RequestMapping(value = "myAppointShow", method = RequestMethod.GET)
    @ResponseBody
    private Result<PageInfo<OwnAppointModel>> myAppointShow(@RequestParam("page") Integer page,HttpServletRequest request) {
        Result<PageInfo<OwnAppointModel>> result = new Result<PageInfo<OwnAppointModel>>();
        //判断是否登录
        
        String usernum = getUserId(request);
        long usernumber=Long.parseLong(usernum);
        System.out.println("$$$$$$ front/appointShow方法的入参为：调用者id:"+usernumber);
        PageInfo<OwnAppointModel> list = conferenceService.myAppointShow(usernumber,page ,10);
        if(list == null) {
            result.setCode("888888");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        System.out.println("$$$$$$ front/appointShow方法的入参为：调用者id:"+usernumber+"front/appointShow方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }

    /**
     *11）我的会议显示	get  front/myConferenceShow
     */
//    @RequestMapping(value = "myConferenceShow", method = RequestMethod.GET)
//    @ResponseBody
//    private Result<List<AppointmentModel>> myConferenceShow(HttpServletRequest request) {
//        Result<List<AppointmentModel>> result = new Result<List<AppointmentModel>>();
//        //判断是否登录
//        if(!checklogin(request)) {
//            result.setCode("999999");
//            result.setMessage("请登录");
//            return result;
//        }
//        String usernum = getUserId(request);
//        long usernumber=Long.parseLong(usernum);
//        System.out.println("$$$$$$ front/appointShow方法的入参为：调用者id:"+usernumber);
//        List<AppointmentModel> list = conferenceService.myConferenceShow(usernumber);
//        result.setCode("000000");
//        result.setMessage("登录成功");
//        result.setData(list);
//        System.out.println("$$$$$$ front/appointShow方法的入参为：调用者id:"+usernumber+"front/appointShow方法的返回参数："+gson.toJson(result)+"#######");
//        return  result;
//    }
    /**
     * 12）空闲会议室查询  post  front/roomSearch(有问题，查出的结果不太对)
     */
    @RequestMapping(value = "roomSearch", method = RequestMethod.POST)
    @ResponseBody
    private Result<List<FreeModel>> roomSearch(@RequestParam("date") String date) {
       // String userid = getUserId(request);
        System.out.println("$$$$$$ front/roomSearch方法的入参为：调用者id:");
        Result<List<FreeModel>> result = new Result<List<FreeModel>>();
        List<FreeModel> list=roomService.freeRoomShow(date);

        if(list == null) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        System.out.println("$$$$$$ front/roomSearch方法的入参为：调用者id:"+"front/roomSearch方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 12）空闲会议室查询  post  front/roomSearch(有问题，查出的结果不太对)
     */
    @RequestMapping(value = "roomSearch2", method = RequestMethod.POST)
    @ResponseBody
    private Result<List<FreeModel>> roomSearch2(@RequestParam("date") String date) {
        // String userid = getUserId(request);
        System.out.println("$$$$$$ front/roomSearch方法的入参为：调用者id:");
        Result<List<FreeModel>> result = new Result<List<FreeModel>>();
        List<FreeModel> list=roomService.freeRoomShow2(date);

        if(list == null) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        System.out.println("$$$$$$ front/roomSearch方法的入参为：调用者id:"+"front/roomSearch方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }


    /**
     13）当前会议显示	get  front/nowConference
     ------
     */
    @RequestMapping(value = "nowConference", method = RequestMethod.POST)
    @ResponseBody
    private Result<List<RoomModel>> nowConference() {
//        String userid = getUserId(request);
//        System.out.println("$$$$$$ front/nowConference方法的入参为：调用者id:"+userid);
        Result<List<RoomModel>> result = new Result<List<RoomModel>>();
        //判断是否登录
//        if(!checklogin(request)) {
//            result.setCode("999999");
//            result.setMessage("请登录");
//            return result;
//        }
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String nowtime = dateFormat.format(date);
        System.out.println(dateFormat.format(date));
//        List<RoomModel> list=
//
//        if(list == null) {
//            result.setCode("999999");
//            result.setMessage("用户不存在");
//        }else {
//            result.setCode("000000");
//            result.setMessage("登录成功");
//            result.setData(list);
//        }
        System.out.println("$$$$$$ front/nowConference方法的入参为：调用者id:userid"+"front/nowConference方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    //取消预约
    @RequestMapping(value = "appointDel", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> appointDel(@RequestParam("appointId") int appointId ,HttpServletRequest request) {
        Result<String> result = new Result<String>();
        //判断是否登录
        
        int num = conferenceService.appointDel(appointId);

        if(num != 0) {
            result.setCode("888888");
            result.setMessage("失败");
        }else {
            result.setCode("000000");
            result.setMessage("删除成功");
        }
        System.out.println("$$$$$$ back/roomDel方法的入参为：调用者id:userid"+"back/roomDel方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 会议预约总情况
     */
    @RequestMapping(value = "appoints", method = RequestMethod.GET)
    @ResponseBody
    private Result<PageInfo<TConferenceRoomAppointment>> appoints(@RequestParam("page") Integer page) {
        Result<PageInfo<TConferenceRoomAppointment>> result = new Result<PageInfo<TConferenceRoomAppointment>>();
        //判断是否登录
//        if(!checklogin(request)) {
//            result.setCode("999999");
//            result.setMessage("请登录");
//            return result;
//        }
        PageInfo<TConferenceRoomAppointment> list = conferenceService.appoints(page ,10);
        if(list == null) {
            result.setCode("888888");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        return  result;
    }


}
