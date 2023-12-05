package com.hbu.models;

public class IconModel {
    private int roomSum;

    private int appointedSum;

    private int freeSum;

    private int maintainSum;

    //今日已预约
    private int sumToday;

    public int getSumToday() {
        return sumToday;
    }

    public void setSumToday(int sumToday) {
        this.sumToday = sumToday;
    }

    public int getRoomSum() {
        return roomSum;
    }

    public void setRoomSum(int roomSum) {
        this.roomSum = roomSum;
    }

    public int getAppointedSum() {
        return appointedSum;
    }

    public void setAppointedSum(int appointedSum) {
        this.appointedSum = appointedSum;
    }

    public int getFreeSum() {
        return freeSum;
    }

    public void setFreeSum(int freeSum) {
        this.freeSum = freeSum;
    }

    public int getMaintainSum() {
        return maintainSum;
    }

    public void setMaintainSum(int maintainSum) {
        this.maintainSum = maintainSum;
    }

    @Override
    public String toString() {
        return "IconModel{" +
                "roomSum=" + roomSum +
                ", appointedSum=" + appointedSum +
                ", freeSum=" + freeSum +
                ", maintainSum=" + maintainSum +
                '}';
    }
}
