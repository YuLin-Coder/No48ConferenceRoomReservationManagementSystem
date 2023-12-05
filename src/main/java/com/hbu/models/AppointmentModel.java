package com.hbu.models;

import com.hbu.entity.TUser;

import java.util.List;

public class AppointmentModel {
	private int appointId;

    private String theme;

    private String date;

    private String time;

    private String starttime;

    private String endtime;

    private Long roomId;

    private int starttimeCode;

    private int endtimeCode;
    private String roomAdress;

    private String roomName;

    private Integer num;

    private int IsEquipment;

    private String specialneeds;

    private String appointerName;

    private String appointerDepartment;

    private long appointerNumber;

    private String examineReason;

    private Integer examineStatus;


    public String getAppointerDepartment() {
        return appointerDepartment;
    }

    public void setAppointerDepartment(String appointerDepartment) {
        this.appointerDepartment = appointerDepartment;
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
        this.examineReason = examineReason;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomAdress() {
        return roomAdress;
    }

    public void setRoomAdress(String roomAdress) {
        this.roomAdress = roomAdress;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public int getStarttimeCode() {
        return starttimeCode;
    }

    public void setStarttimeCode(int starttimeCode) {
        this.starttimeCode = starttimeCode;
    }

    public int getEndtimeCode() {
        return endtimeCode;
    }

    public void setEndtimeCode(int endtimeCode) {
        this.endtimeCode = endtimeCode;
    }


	public int getIsEquipment() {
        return IsEquipment;
    }

    public void setIsEquipment(int isEquipment) {
        IsEquipment = isEquipment;
    }

    public String getSpecialneeds() {
        return specialneeds;
    }

    public void setSpecialneeds(String specialneeds) {
        this.specialneeds = specialneeds;
    }

    public String getAppointerName() {
        return appointerName;
    }

    public void setAppointerName(String appointerName) {
        this.appointerName = appointerName;
    }

    public long getAppointerNumber() {
        return appointerNumber;
    }

    public void setAppointerNumber(long appointerNumber) {
        this.appointerNumber = appointerNumber;
    }

    public int getAppointId() {
        return appointId;
    }

    public void setAppointId(int appointId) {
        this.appointId = appointId;
    }


}
