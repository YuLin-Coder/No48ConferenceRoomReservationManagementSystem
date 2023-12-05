package com.hbu.entity;

public class RefConference {
    private Integer id;

    private Long conferenceRoomId;

    private Integer conferenceRoomAppointmentId;

    private String date;

    private String time;

    private Integer starttimeCode;

    private Integer endtimeCode;

    private Boolean isdel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getConferenceRoomId() {
        return conferenceRoomId;
    }

    public void setConferenceRoomId(Long conferenceRoomId) {
        this.conferenceRoomId = conferenceRoomId;
    }

    public Integer getConferenceRoomAppointmentId() {
        return conferenceRoomAppointmentId;
    }

    public void setConferenceRoomAppointmentId(Integer conferenceRoomAppointmentId) {
        this.conferenceRoomAppointmentId = conferenceRoomAppointmentId;
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

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }
}