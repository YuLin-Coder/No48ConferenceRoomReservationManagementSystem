package com.hbu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbu.dao.TNoticeMapper;
import com.hbu.dao.TRulesMapper;
import com.hbu.dao.TUserMapper;
import com.hbu.entity.*;
import com.hbu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private TNoticeMapper tNoticeMapper;
    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private TRulesMapper tRulesMapper;

    @Override
    public PageInfo<TNotice> show(int page){
        // 页码、每页的大小
        PageHelper.startPage(page, 20,true);
        TNoticeExample tNoticeExample = new TNoticeExample();
        TNoticeExample.Criteria criteria = tNoticeExample.createCriteria();
        criteria.andIsdelEqualTo(true);
        List<TNotice> list=tNoticeMapper.selectByExample(tNoticeExample);
        PageInfo<TNotice> pageInfo= new PageInfo<>(list);
        if(list != null && list.size() > 0) {
            return pageInfo;
        }
        else {
            return null;
        }
    }
    @Override
    public TNotice showdetails(int id){
       /* TNoticeExample tNoticeExample =new TNoticeExample();
        TNoticeExample.Criteria criteria = tNoticeExample.createCriteria();
        criteria.andIsdelEqualTo(true);*/
    	TNotice t=tNoticeMapper.selectByPrimaryKey(id);
        if(t != null) {
            return t;
        }
        else {
            return null;
        }
    }
    @Override
    public int insert(TNotice t){
    	//登录的uuid没有弄好，暂时没法用getAuthoruuid
//        String uuid = t.getAuthoruuid();
//        TUser user = tUserMapper.selectByUUid(uuid);
//        t.setAuthorName(user.getName());
//        t.setAuthorNumber(user.getNumber());
        int num = tNoticeMapper.insert(t);
        //不成功返回0
        if(num == 0) {
            return 0;
        }else {
            return num;
        }
    }
    @Override
    public TRules ruleshow(){
        TRulesExample example = new TRulesExample();
        List<TRules> list =tRulesMapper.selectByExampleWithBLOBs(example);
        TRules rule = new TRules();
        rule.setId(list.get(0).getId());
        rule.setAuthorName(list.get(0).getAuthorName());
        rule.setTitle(list.get(0).getTitle());
        rule.setContent(list.get(0).getContent());
        rule.setCreatTime(list.get(0).getCreatTime());
        if(rule != null ) {
            return rule;
        }
        else {
            return null;
        }
    }



}
