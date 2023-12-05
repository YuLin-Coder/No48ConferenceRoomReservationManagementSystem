package com.hbu.models;

import java.util.Date;

public class NoticeModel {
    private Integer noticeId;

    private String title;

    private String authoruuid;

    private long authorNumber;

    private String authorName;

    private Date creatTime;

    private Date updateTime;

    private String content;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthoruuid() {
        return authoruuid;
    }

    public void setAuthoruuid(String authoruuid) {
        this.authoruuid = authoruuid;
    }

    public long getAuthorNumber() {
        return authorNumber;
    }

    public void setAuthorNumber(long authorNumber) {
        this.authorNumber = authorNumber;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoticeModel{" +
                "noticeId=" + noticeId +
                ", title='" + title + '\'' +
                ", authoruuid='" + authoruuid + '\'' +
                ", authorNumber=" + authorNumber +
                ", authorName='" + authorName + '\'' +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                ", content='" + content + '\'' +
                '}';
    }
}
