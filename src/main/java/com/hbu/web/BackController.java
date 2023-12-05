package com.hbu.web;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.hbu.dao.TNoticeMapper;
import com.hbu.entity.TConferenceRoomAppointment;
import com.hbu.entity.TNotice;
import com.hbu.entity.TUser;
import com.hbu.models.*;
import com.hbu.service.*;
import com.hbu.utils.RedisUtil;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 刘思彤 2020/04/30
 * 后台back controller
 */

@Controller
@RequestMapping("/back")
public class BackController extends BaseController {
    private static Gson gson = new Gson();//将对象转成json类型字符串的工具
    //注入service依赖
    @Autowired
    private LoginService loginService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private WaitPassService waitPassService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserManageService userManageService;
    @Autowired
    private ConferenceService conferenceService;
    @Resource
    private TNoticeMapper tNoticeMapper;
    /**
     * 判断是否登录 ..需要 重写
     */

    //1.后台登录 back/login——只有学校后勤管理人员可以登录
    @RequestMapping(value = "adminLogin", method = RequestMethod.GET)
    @ResponseBody
    private Result<String> login( @RequestParam("number") long number, @RequestParam("password") String password ) {
        System.out.println("$$$$$$ back/login2方法的入参为：number="+number+",password="+password);
        Result<String> result=new Result<String>();
        int tAdmin=loginService.backlogin(number,password);

        if(tAdmin==-1) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else if (tAdmin==0){
            result.setCode("888888");
            result.setMessage("密码错误");
        } else {
            result.setCode("000000");
            result.setMessage("登录成功");
            /**
             * 设置cookie，并存到redis中。
             * 但是redis的value值，目前是number值。。。。实际应该是uuid（将number随机也行）
             */
            String token2=getId().replace("-", "");
            RedisUtil.set(token2,String.valueOf(number));
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(token2);
        }
        System.out.println("$$$$$$ back/login2方法的入参为：number="+number+",password="+password+"back/login2方法的返回参数："+gson.toJson(result));
        return result;
    }

    /**
     * 管理员忘记密码,发送邮件接口
     */
    @RequestMapping(value = "forgetpw", method = RequestMethod.GET)
    @ResponseBody
    private Result<String> forgetpw( @RequestParam("number") long number, @RequestParam("mailbox") String mailbox ) {
        System.out.println("$$$$$$ back/login2方法的入参为：number="+number+",mailbox="+mailbox);
        Result<String> result=new Result<String>();
        int tAdmin=loginService.admineforgetpw(number,mailbox);
        System.out.println(tAdmin);
        if(tAdmin==-1) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else if (tAdmin==0){
            result.setCode("888888");
            result.setMessage("邮箱错误");
        } else {
            result.setCode("000000");
            result.setMessage("发送成功");
        }
        System.out.println("$$$$$$ back/login2方法的入参为：number="+number+",mailbox="+mailbox+"back/login2方法的返回参数："+gson.toJson(result));
        return result;
    }

    /**
     * 2.公告信息展示：get、url：/back/noticeShow
     * ++++++(时间不行)
     */
    @RequestMapping(value = "noticeShow", method = RequestMethod.GET)
    @ResponseBody
    private Result<PageInfo<TNotice>> noticeShow(HttpServletRequest request,@RequestParam("page") int page) {
        Result<PageInfo<TNotice>> result = new Result<PageInfo<TNotice>>();
        //判断是否登录
       
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
     *3.信息详细展示：get、url：/back/noticeShowDetails
     * ++++++(时间不行)
     */
    @RequestMapping(value = "noticeShowDetails", method = RequestMethod.GET)
    @ResponseBody
    private Result<TNotice> noticeShowDetails(@RequestParam("noticeId") int noticeId ,HttpServletRequest request) {
        Result<TNotice> result = new Result<TNotice>();
        //判断是否登录
       
        TNotice t=noticeService.showdetails(noticeId);//查出来的所有数据

        if(t == null) {
            result.setCode("888888");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(t);
        }
        return  result;
    }
    /**
     *4.待审核显示(管理员页面)  get、url：/back/waitPassShow
     *+++++++++
     */
    @RequestMapping(value = "waitPassShow", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<WaitPassModel>> waitPassShow(HttpServletRequest request) {
//        String userid  = getUserId(request);
//        System.out.println("$$$$$$ back/waitPassShow方法的入参为：调用者id:"+userid);
        Result<List<WaitPassModel>> result = new Result<List<WaitPassModel>>();
        //判断是否登录
        String userid = getUserId2(request);
       
        List<WaitPassModel> list=waitPassService.showAll();

        if(list == null) {
            result.setCode("111111");
            result.setMessage("暂无数据");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        System.out.println("$$$$$$ back/waitPassShow方法的入参为：调用者id:"+"back/waitPassShow方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     *4.2待审核显示(系主任页面)  get、url：/back/waitPassShow2
     *+++++++++
     */
//    @RequestMapping(value = "waitPassShow2", method = RequestMethod.GET)
//    @ResponseBody
//    private Result<List<WaitPassModel>> waitPassShow2() {//HttpServletRequest request
////        String userid = getUserId(request);
////        System.out.println("$$$$$$ back/waitPassShow方法的入参为：调用者id:"+userid);
//        Result<List<WaitPassModel>> result = new Result<List<WaitPassModel>>();
//        //判断是否登录
////        if(!checklogin(request)) {
////            result.setCode("999999");
////            result.setMessage("请登录");
////            return result;
////        }
//        List<WaitPassModel> list=waitPassService.showAll2();
//
//        if(list == null) {
//            result.setCode("999999");
//            result.setMessage("用户不存在");
//        }else {
//            result.setCode("000000");
//            result.setMessage("登录成功");
//            result.setData(list);
//        }
//        System.out.println("$$$$$$ back/waitPassShow方法的入参为：调用者id:"+"back/waitPassShow方法的返回参数："+gson.toJson(result)+"#######");
//        return  result;
//    }

    /**
     * 5.待审核处理 通过（管理员）   post、url：/back/waitPass1
     *++++++++++++++++
     */
    @RequestMapping(value = "waitPass1", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> waitPass1(@RequestParam("appointmentId") int appointmentId,HttpServletRequest request) {
//        String userid = getUserId(request);
//        System.out.println("$$$$$$ back/waitPassShow方法的入参为：调用者id:"+userid);
        Result<String> result = new Result<String>();
        //判断是否登录
       
        int num= waitPassService.waitpass1(appointmentId);
        if(num != 0) {
            result.setCode("111111");
            result.setMessage("操作失败，请重新预约");
        }else {
            //nn=0
            result.setCode("000000");
            result.setMessage("操作成功");
        }
        System.out.println("$$$$$$ back/waitPass1方法的入参为：调用者id:"+"back/waitPass1方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 5.2待审核处理,不通过（管理员）   post、url：/back/waitNoPass1
     * +++++++++++++
     */
    @RequestMapping(value = "waitNoPass1", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> waitNoPass1(@RequestParam("appointmentId") int appointmentId,@RequestParam("reason") String reason,HttpServletRequest request) {
//        String userid = getUserId(request);
//        System.out.println("$$$$$$ back/waitPassShow方法的入参为：调用者id:"+userid);
        Result<String> result = new Result<String>();
        //判断是否登录
       
        int num= waitPassService.waitnopass1(appointmentId,reason);

        if(num == 0) {
            result.setCode("888888");
            result.setMessage("操作失败");
        }else {
            result.setCode("000000");
            result.setMessage("操作成功");
        }
        System.out.println("$$$$$$ back/waitPass1方法的入参为：调用者id:"+"back/waitPass1方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     *6.待审核处理（系主任）   post、url：/back/waitPass2
     */
//    @RequestMapping(value = "waitPass2", method = RequestMethod.POST)
//    @ResponseBody
//    private Result<String> waitPass2(@RequestParam("appointmentId") int appointmentId, HttpServletRequest request) {
//        String userid = getUserId(request);
//        System.out.println("$$$$$$ back/waitPassShow方法的入参为：调用者id:"+userid);
//        Result<String> result = new Result<String>();
//        //判断是否登录
//        if(!checklogin(request)) {
//            result.setCode("999999");
//            result.setMessage("请登录");
//            return result;
//        }
//        int num= waitPassService.waitpass2(appointmentId);
//        if(num == 0) {
//            result.setCode("999999");
//            result.setMessage("操作失败");
//        }else {
//            result.setCode("000000");
//            result.setMessage("操作成功");
//        }
//        System.out.println("$$$$$$ back/waitPass2方法的入参为：调用者id:"+userid+"back/waitPass2方法的返回参数："+gson.toJson(result)+"#######");
//        return  result;
//    }

    /**
     * 不通过de,还没写。只需要将status改成0或者3.
     * 同上
     */

    /**
     * 7.会议室管理——显示   get、url：/back/roomShow
     * ++++++
     */
    @RequestMapping(value = "roomShow", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<RoomModel>> roomShow(HttpServletRequest request) {
//        String userid = getUserId(request);
       System.out.println("$$$$$$ back/roomShow方法的入参为：调用者id:+userid");
        Result<List<RoomModel>> result = new Result<List<RoomModel>>();
        //判断是否登录
       
        List<RoomModel> list=roomService.show3();

        if(list == null) {
            result.setCode("888888");
            result.setMessage("失败");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        System.out.println("$$$$$$ back/roomShow方法的入参为：调用者id:userid"+"back/roomShow方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 8.会议室管理——添加   post、url：/back/roomInsert
     * ++++++++++++++++++++++++++
     */
    @RequestMapping(value = "roomInsert", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> roomInsert(@RequestParam("roomName") String roomName,@RequestParam("roomType") String roomType,@RequestParam("address") String address, @RequestParam("time") String time, @RequestParam("capacity") int capacity,@RequestParam("status") int status,@RequestParam("equipment") String equipment,HttpServletRequest request) {
      //  String userid = getUserId(request);
        System.out.println("$$$$$$ back/roomInsert方法的入参为：调用者id:");
        Result<String> result = new Result<String>();
        //判断是否登录
       
        //List<RoomModel> listmodel=new ArrayList<RoomModel>();
        RoomModel model = new RoomModel();
        model.setName(roomName);
        model.setType(roomType);
        model.setAddress(address);
        model.setCapacity(capacity);
        model.setStatus(status);
        model.setEquipment(equipment);
        model.setAvailableTime(time);
        //listmodel.add(model);
        int num = roomService.insert(model);

        if(num == 0) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
        }
        System.out.println("$$$$$$ back/roomInsert方法的入参为：调用者id:"+"useid"+"back/roomInsert方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 9.会议室管理——编辑   post、url：/back/roomUpdate
     * ++++++++++++++++++
     */
    @RequestMapping(value = "roomUpdate", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> roomUpdate(@RequestParam("rommId") long roomId,@RequestParam("roomName") String roomName,@RequestParam("roomType") String roomType,@RequestParam("address") String address, @RequestParam("time") String time,@RequestParam("capacity") int capacity,@RequestParam("status") int status,@RequestParam("equipment") String equipment,HttpServletRequest request) {
//        String userid = getUserId(request);
        System.out.println("$$$$$$ back/roomUpdate方法的入参为：调用者id:");
        Result<String> result = new Result<String>();
        //判断是否登录
       
        RoomModel model = new RoomModel();
        model.setId(roomId);
        model.setName(roomName);
        model.setType(roomType);
        model.setAddress(address);
        model.setAvailableTime(time);
        model.setCapacity(capacity);
        model.setStatus(status);
        model.setEquipment(equipment);
        int num = roomService.update(model);

        if(num == 0) {
            result.setCode("888888");
            result.setMessage("失败");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
        }
        System.out.println("$$$$$$ back/roomUpdate方法的入参为：调用者id:"+"sdg"+"back/roomUpdate方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 10.会议室管理——删除   post、url：/back/roomDel
     * +++++++++++++
     */
    @RequestMapping(value = "roomDel", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> roomDel(@RequestParam("rommId") long roomId ,HttpServletRequest request) {
//        String userid = getUserId(request);
        System.out.println("$$$$$$ back/roomDel方法的入参为：调用者id:+userid");
        Result<String> result = new Result<String>();
        //判断是否登录
       
        int num = roomService.del(roomId);

        if(num != 0) {
            result.setCode("111111");
            result.setMessage("该会议室已经被教师所预约，不能删除");
        }else {
            result.setCode("000000");
            result.setMessage("删除成功");
        }
        System.out.println("$$$$$$ back/roomDel方法的入参为：调用者id:userid"+"back/roomDel方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 11.用户管理——显示   get、url：/back/userShow
     * +++++++
     */
    @RequestMapping(value = "userShow", method = RequestMethod.GET)
    @ResponseBody
    private Result<PageInfo<TUser>> userShow(HttpServletRequest request,@RequestParam("page") int page) {
//        String userid = getUserId(request);
//        System.out.println("$$$$$$ back/userShow方法的入参为：调用者id:"+userid);
        Result<PageInfo<TUser>> result = new Result<PageInfo<TUser>>();
        //判断是否登录
       
        PageInfo<TUser> list= userManageService.show(page);

        if(list == null) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        System.out.println("$$$$$$ back/userShow方法的入参为：调用者id:"+"back/userShow方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 12.用户管理——添加   post、url：/back/userInsert
     * ++++++++++++++++++++++++++
     */
    @RequestMapping(value = "userInsert", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> userInsert(@RequestParam("number") long number,@RequestParam("name") String name,@RequestParam("password") String password,@RequestParam("department") String department, @RequestParam("phone") String phone, @RequestParam("mailbox") String mailbox,HttpServletRequest request) throws ParseException {
      //  String userid = getUserId(request);
        System.out.println("$$$$$$ back/userInsert方法的入参为：调用者id:");
        Result<String> result = new Result<String>();
        //判断是否登录
       
        //List<RoomModel> listmodel=new ArrayList<RoomModel>();
        TUser t = new TUser();      
        t.setNumber(number);
        t.setName(name);
        t.setPassword(password);
        t.setDepartment(department);
        t.setPhone(phone);
        t.setMailbox(mailbox);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String creatTime = df.format(new Date());
        Date date = df.parse(creatTime);
        t.setCreatTime(date);
        int num = userManageService.insert(t);

        if(num == 0) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
        }
        System.out.println("$$$$$$ back/userInsert方法的入参为：调用者id:"+"useid"+"back/userInsert方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 13.用户管理——编辑   post、url：/back/userUpdate
     * ++++++++++++++++++
     */
    @RequestMapping(value = "userUpdate", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> userUpdate(@RequestParam("userId") int userId,@RequestParam("number") long number,@RequestParam("name") String name,@RequestParam("password") String password,@RequestParam("department") String department, @RequestParam("phone") String phone, @RequestParam("mailbox") String mailbox,HttpServletRequest request) {
//        String userid = getUserId(request);
        System.out.println("$$$$$$ back/userUpdate方法的入参为：调用者id:");
        Result<String> result = new Result<String>();
        //判断是否登录
       
        TUser t = new TUser(); 
        t.setId(userId);
        t.setNumber(number);
        t.setName(name);
        t.setPassword(password);
        t.setDepartment(department);
        t.setPhone(phone);
        t.setMailbox(mailbox);
        int num = userManageService.update(t);

        if(num == 0) {
            result.setCode("999999");
            result.setMessage("用户不存在");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
        }
        System.out.println("$$$$$$ back/userUpdate方法的入参为：调用者id:"+"sdg"+"back/userUpdate方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 14.用户管理——删除   post、url：/back/userDel
     * +++++++++++++
     */
    @RequestMapping(value = "userDel", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> userDel(@RequestParam("userId") int userId, HttpServletRequest request ) {
//        String userid = getUserId(request);
        System.out.println("$$$$$$ back/userDel方法的入参为：调用者id:+userid");
        Result<String> result = new Result<String>();
        //判断是否登录
       
        int num = userManageService.del(userId);

        if(num != 0) {
            result.setCode("111111");
            result.setMessage("该用户有预约申请，不能删除");
        }else {
            result.setCode("000000");
            result.setMessage("删除成功");
        }
        System.out.println("$$$$$$ back/userDel方法的入参为：调用者id:userid"+"back/userDel方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    
    /**
     * 15.信息发布删除   post、url：/back/noticeDel
     * +++++++++++（时间不行，应该改数据库字段）
     */
    @RequestMapping(value = "noticeDel", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> noticeDel(@RequestParam("id") String ids,HttpServletRequest request ) {
        Result<String> result = new Result<String>();
        //判断是否登录
        String userid = getUserId2(request);
       
        int num =0;
        String[] arr = ids.split(",");
        if(arr.length !=0 && arr !=null){
            for(String id :arr){
                TNotice t = new TNotice();
                t.setIsdel(false);
                t.setId(Integer.parseInt(id));
                num = tNoticeMapper.updateByPrimaryKey(t);
            }
        }
        if(num == 0) {
            result.setCode("888888");
            result.setMessage("失败");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
        }
        return  result;
    }
    /**
     * 15.信息发布添加   post、url：/back/noticeInsert
     * +++++++++++（时间不行，应该改数据库字段）
     */
    @RequestMapping(value = "noticeInsert", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> noticeInsert(@RequestParam("content") String content,@RequestParam("title") String title,@RequestParam("author") String author ,HttpServletRequest request ) throws ParseException {
//        String userid = getUserId(request);
//        System.out.println("$$$$$$ back/noticeInsert方法的入参为：调用者id:"+userid);
        Result<String> result = new Result<String>();
        //判断是否登录
        String userid = getUserId2(request);
       
        TNotice t = new TNotice();
        t.setAuthorName(author);
        t.setContent(content);
        t.setTitle(title);
        t.setIsdel(true);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String creatTime = df.format(new Date());
        Date date = df.parse(creatTime);
        t.setCreatTime(date);
        int num = noticeService.insert(t);

        if(num == 0) {
            result.setCode("888888");
            result.setMessage("失败");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
        }
        System.out.println("$$$$$$ back/noticeInsert方法的入参为：调用者id:"+"back/noticeInsert方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     *16.未通过审核  get、url：/back/nopass
     *+++++++++
     */
    @RequestMapping(value = "nopass", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<WaitPassModel>> nopass(HttpServletRequest request) {//
//        String userid  = getUserId(request);
//        System.out.println("$$$$$$ back/waitPassShow方法的入参为：调用者id:"+userid);
        Result<List<WaitPassModel>> result = new Result<List<WaitPassModel>>();
        //判断是否登录
        String userid = getUserId2(request);
       
        List<WaitPassModel> list=waitPassService.showNoPass();

        if(list == null) {
            result.setCode("999999");
            result.setMessage("用户不存在，发生异常，数据库数据错误");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        System.out.println("$$$$$$ back/waitPassShow方法的入参为：调用者id:"+"back/waitPassShow方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    /**
     * 新用户审核显示
     */
    @RequestMapping(value = " newuser", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<TUser>> newuser(HttpServletRequest request) {
        Result<List<TUser>> result = new Result<List<TUser>>();
        //判断是否登录
        String userid = getUserId2(request);
       
        List<TUser> list=userManageService.newuser();

        if(list == null) {
            result.setCode("111111");
            result.setMessage("暂无数据");
        }else {
            result.setCode("000000");
            result.setMessage("登录成功");
            result.setData(list);
        }
        System.out.println("$$$$$$ back/waitPassShow方法的入参为：调用者id:"+"back/waitPassShow方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }

    @RequestMapping(value = "userpass", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> userpass(@RequestParam("userId") int id,HttpServletRequest request) {
        Result<String> result = new Result<String>();
        //判断是否登录
        String userid = getUserId2(request);
       
        int num= waitPassService.userpass(id);

        if(num == 0) {
            result.setCode("888888");
            result.setMessage("操作失败");
        }else {
            result.setCode("000000");
            result.setMessage("操作成功");
        }
        System.out.println("$$$$$$ back/waitPass1方法的入参为：调用者id:"+"back/waitPass1方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }
    @RequestMapping(value = "usernopass", method = RequestMethod.POST)
    @ResponseBody
    private Result<String> usernopass(@RequestParam("userId") int id,@RequestParam("reason") String reason, HttpServletRequest request) {
        Result<String> result = new Result<String>();
        //判断是否登录
        String userid = getUserId2(request);
       
        int num= waitPassService.usernopass(id,reason);

        if(num == 0) {
            result.setCode("888888");
            result.setMessage("操作失败");
        }else {
            result.setCode("000000");
            result.setMessage("操作成功");
        }
        System.out.println("$$$$$$ back/waitPass1方法的入参为：调用者id:"+"back/waitPass1方法的返回参数："+gson.toJson(result)+"#######");
        return  result;
    }

    /**
     * 会议预约总情况
     */
    @RequestMapping(value = "appoints", method = RequestMethod.GET)
    @ResponseBody
    private Result<PageInfo<TConferenceRoomAppointment>> appoints(@RequestParam("page") Integer page, HttpServletRequest request) {
        Result<PageInfo<TConferenceRoomAppointment>> result = new Result<PageInfo<TConferenceRoomAppointment>>();
        //判断是否登录
       
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

