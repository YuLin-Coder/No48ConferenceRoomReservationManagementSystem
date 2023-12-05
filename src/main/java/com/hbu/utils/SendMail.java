package com.hbu.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * lst
 * 2020/04/26
 * 参考：https://blog.csdn.net/qq_35794278/article/details/80793641
 * https://blog.csdn.net/qq_41151659/article/details/96475739
 */
public class SendMail {
    public static int sendMessage(String receiver,String messagecontent){
        //做链接前的准备工作  也就是参数初始化
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host","smtp.qq.com");//发送邮箱服务器
        properties.setProperty("mail.smtp.port","465");//发送端口
        properties.setProperty("mail.smtp.auth","true");//是否开启权限控制
        properties.setProperty("mail.debug","true");//true 打印信息到控制台
        properties.setProperty("mail.transport","smtp");//发送的协议是简单的邮件传输协议
        properties.setProperty("mail.smtp.ssl.enable","true");
        //建立两点之间的链接
        System.out.println("执行了2");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("qq邮箱","密钥");
            }
        });
        System.out.println("执行了3");
        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        try {
            message.setFrom(new InternetAddress("qq邮箱"));

            //设置收件人
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(receiver));//收件人
            //设置主题
            message.setSubject("您在管理学院会议室管理系统的申请结果出来了,请注意查收！");
            //设置邮件正文  第二个参数是邮件发送的类型
            message.setContent(messagecontent,"text/html;charset=UTF-8");
            //发送一封邮件
            Transport transport = session.getTransport();
            transport.connect("QQ邮箱","密钥");
            Transport.send(message);
            System.out.println("执行了");
        } catch (MessagingException e) {
            e.printStackTrace();
        }finally {

        }
        return 1;

    }
    public static int sendMessage(String receiver,String messagecontent,String title){
        //做链接前的准备工作  也就是参数初始化
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host","smtp.qq.com");//发送邮箱服务器
        properties.setProperty("mail.smtp.port","465");//发送端口
        properties.setProperty("mail.smtp.auth","true");//是否开启权限控制
        properties.setProperty("mail.debug","true");//true 打印信息到控制台
        properties.setProperty("mail.transport","smtp");//发送的协议是简单的邮件传输协议
        properties.setProperty("mail.smtp.ssl.enable","true");
        //建立两点之间的链接
        System.out.println("执行了2");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("qq邮箱","密钥");
            }
        });
        System.out.println("执行了3");
        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        try {
            message.setFrom(new InternetAddress("qq邮箱"));

            //设置收件人
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(receiver));//收件人
            //设置主题
            message.setSubject("您在管理学院会议室管理系统的申请结果出来了,请注意查收！");
            //设置邮件正文  第二个参数是邮件发送的类型
            message.setContent(messagecontent,"text/html;charset=UTF-8");
            //发送一封邮件
            Transport transport = session.getTransport();
            transport.connect("邮箱","密钥");
            Transport.send(message);
            System.out.println("执行了");
        } catch (MessagingException e) {
            e.printStackTrace();
        }finally {

        }
        return 1;
    }
}
