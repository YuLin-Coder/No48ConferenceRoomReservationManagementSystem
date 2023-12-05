package com.hbu.models;

public class RoomModel {
    private Long id;//会议室id

    private String name;

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
		return "RoomModel [id=" + id + ", name=" + name + ", address=" + address + ", capacity=" + capacity
				+ ", status=" + status + ", equipment=" + equipment + ", conferenceRoomTypeCode="
				+ conferenceRoomTypeCode + ", type=" + type + ", directorName=" + directorName + ", availableTime="
				+ availableTime + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
