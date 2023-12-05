package com.hbu.models;

public class FreeModel {
    /**
     * 查出的会议相应的————con前缀
     */
    private int confenenceId;
    private long conRoomId;
    private String conRoomName;
    private String conRoomType;
    private String time;


    private Long roomId;//会议室id

    private String roomName;

    private String address;

    private Integer capacity;

    private Integer status;

    private String equipment;

    private Integer conferenceRoomTypeCode;

    private String type;//会议室类型

    private String directorName;//系主任名字

    private String availableTime;

    @Override
    public String toString() {
        return "FreeModel{" +
                "confenenceId=" + confenenceId +
                ", conRoomId=" + conRoomId +
                ", conRoomName='" + conRoomName + '\'' +
                ", conRoomType=" + conRoomType +
                ", time='" + time + '\'' +
                ", roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                ", status=" + status +
                ", equipment='" + equipment + '\'' +
                ", conferenceRoomTypeCode=" + conferenceRoomTypeCode +
                ", type='" + type + '\'' +
                ", directorName='" + directorName + '\'' +
                ", availableTime='" + availableTime + '\'' +
                '}';
    }

    public int getConfenenceId() {
        return confenenceId;
    }

    public void setConfenenceId(int confenenceId) {
        this.confenenceId = confenenceId;
    }

    public long getConRoomId() {
        return conRoomId;
    }

    public void setConRoomId(long conRoomId) {
        this.conRoomId = conRoomId;
    }

    public String getConRoomName() {
        return conRoomName;
    }

    public void setConRoomName(String conRoomName) {
        this.conRoomName = conRoomName;
    }

    public String getConRoomType() {
        return conRoomType;
    }

    public void setConRoomType(String conRoomType) {
        this.conRoomType = conRoomType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public Integer getConferenceRoomTypeCode() {
        return conferenceRoomTypeCode;
    }

    public void setConferenceRoomTypeCode(Integer conferenceRoomTypeCode) {
        this.conferenceRoomTypeCode = conferenceRoomTypeCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }
}
