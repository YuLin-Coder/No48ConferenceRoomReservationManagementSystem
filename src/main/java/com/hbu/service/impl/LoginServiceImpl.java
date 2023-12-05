package com.hbu.service.impl;

import com.hbu.dao.TAdminMapper;
import com.hbu.dao.TUserMapper;
import com.hbu.entity.TUser;
import com.hbu.service.LoginService;
import com.hbu.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl  implements LoginService {
    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private TAdminMapper tAdminMapper;

    @Override
    public int backlogin(long number, String password){
        String pw=tAdminMapper.selectByNumber(number);
        System.out.println(pw);
        if (pw == null){
            return -1;
        }
        else if(!pw.equals(password)){
            return 0;
        }
        return 1;
    }

    @Override
    public int admineforgetpw(long number, String mailbox) {
        String mail = tAdminMapper.selectMailByNumber(number);
        System.out.println(mail);
        if (mail == null){
            return -1;
        }
        else if(!mail.equals(mailbox)){
            return 0;
        }else{
            String pw=tAdminMapper.selectByNumber(number);
            SendMail.sendMessage(mail,"你的密码是："+pw);
            return 1;
        }
    }

    @Override
    public int frontlogin(long number, String password){
        String pw = tUserMapper.selectByNumber(number);
        if (pw == null){
            return -1;
        }
        else if(!pw.equals(password)){
            return 0;
        }else{
            return 1;
        }

    }

    @Override
    public int userforgetpw(long number, String mailbox) {

        String mail = tUserMapper.selectMailByNumber(number);
        System.out.println(mail);
        if (mail == null){
            return -1;
        }
        else if(!mail.equals(mailbox)){
            return 0;
        }else{
            String pw=tUserMapper.selectByNumber(number);
            SendMail.sendMessage(mail,"您的密码是："+pw);
            return 1;
        }
    }
    @Override
    public int userregister(long number, String mailbox,String pw,String name,String department){
        TUser user = tUserMapper.selectAllByNumber(number);
        if (user == null){
            //null说明没有注册过
            TUser t = new TUser();
            t.setNumber(number);
            t.setMailbox(mailbox);
            t.setPassword(pw);
            t.setDepartment(department);
            t.setName(name);
            t.setIsdel(true);
            t.setStatus((short) 0);
        //不能直接注册，需要审核（默认为未处理）
            // return 1;
            if(tUserMapper.insert(t) !=0 ){
                return 1;
            }else{
                return 0;
            }
        } else{
            return 0;
        }

    }
}
