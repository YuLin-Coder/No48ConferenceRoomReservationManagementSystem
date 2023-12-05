package com.hbu.models;

public class WaitPassModel {
    private Integer appointmentId;//预约id

    private String username;

    private long number;

    private String department;

    private Long conferenceRoomId;//预约会议室id

    private String roomName;

    private String roomType;

    private Integer maxNum;

    private String theme;
    
    private String date;

    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

    private String time;

    private Integer starttimeCode;

    private Integer endtimeCode;

    private Integer examineStatus;//审核状态

    public String getExamineReason() {
        return examineReason;
    }

    public void setExamineReason(String examineReason) {
        this.examineReason = examineReason;
    }

    private String examineReason;//审核理由

    private String specialneeds;

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getConferenceRoomId() {
        return conferenceRoomId;
    }

    public void setConferenceRoomId(Long conferenceRoomId) {
        this.conferenceRoomId = conferenceRoomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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
        this.theme = theme;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(Integer examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getSpecialneeds() {
        return specialneeds;
    }

    public void setSpecialneeds(String specialneeds) {
        this.specialneeds = specialneeds;
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

    @Override
    public String toString() {
        return "WaitPassModel{" +
                "appointmentId=" + appointmentId +
                ", username='" + username + '\'' +
                ", number=" + number +
                ", department='" + department + '\'' +
                ", conferenceRoomId=" + conferenceRoomId +
                ", roomName='" + roomName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", maxNum=" + maxNum +
                ", theme='" + theme + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", starttimeCode=" + starttimeCode +
                ", endtimeCode=" + endtimeCode +
                ", examineStatus=" + examineStatus +
                ", examineReason='" + examineReason + '\'' +
                ", specialneeds='" + specialneeds + '\'' +
                '}';
    }
}
