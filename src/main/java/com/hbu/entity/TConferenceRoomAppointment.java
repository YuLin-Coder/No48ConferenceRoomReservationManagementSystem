package com.hbu.entity;


import javax.persistence.Transient;

public class TConferenceRoomAppointment {

    /**
     *  @Transient
     *Spring 注解实体类中非数据库字段属性
     */
    @Transient
    private String conferenceRoomName;

    private Integer id;

    private String username;

    private Long number;

    private Long conferenceRoomId;

    private Integer maxNum;

    private String theme;

    private String date;

    private String time;

    private Integer starttimeCode;

    private Integer endtimeCode;

    private Integer examineStatus;

    private String examineReason;

    private Boolean isdel;

    private String specialneeds;

    public String getConferenceRoomName() {
        return conferenceRoomName;
    }

    public void setConferenceRoomName(String conferenceRoomName) {
        this.conferenceRoomName = conferenceRoomName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getConferenceRoomId() {
        return conferenceRoomId;
    }

    public void setConferenceRoomId(Long conferenceRoomId) {
        this.conferenceRoomId = conferenceRoomId;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getStarttimeCode() {
        return starttimeCode;
    }

    public void setStarttimeCode(Integer starttimeCode) {
        this.starttimeCode = starttimeCode;
    }

    public Integer getEndtimeCode() {
        return endtimeCode;
    }

    public void setEndtimeCode(Integer endtimeCode) {
        this.endtimeCode = endtimeCode;
    }

    public Integer getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(Integer examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getExamineReason() {
        return examineReason;
    }

    public void setExamineReason(String examineReason) {
        this.examineReason = examineReason == null ? null : examineReason.trim();
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public String getSpecialneeds() {
        return specialneeds;
    }

    public void setSpecialneeds(String specialneeds) {
        this.specialneeds = specialneeds == null ? null : specialneeds.trim();
    }
}