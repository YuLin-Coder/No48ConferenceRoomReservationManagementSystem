package com.hbu.service;

import com.github.pagehelper.PageInfo;
import com.hbu.entity.TNotice;
import com.hbu.entity.TRules;

import java.util.List;

public interface NoticeService {
    public PageInfo<TNotice> show(int page);
    
    public TNotice showdetails(int noticeId);
    
    public int insert(TNotice t);

    public TRules ruleshow();
}
