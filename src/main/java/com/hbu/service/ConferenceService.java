package com.hbu.service;

import com.github.pagehelper.PageInfo;
import com.hbu.entity.TConferenceRoomAppointment;
import com.hbu.models.AppointmentModel;
import com.hbu.models.AppointsModel;
import com.hbu.models.IconModel;
import com.hbu.models.OwnAppointModel;

import java.util.List;

public interface ConferenceService {

     IconModel iconshow();

     int ownerinsert(AppointmentModel model);

     int otherinsert(AppointmentModel model);

    PageInfo<OwnAppointModel> myAppointShow(long number, int page, int size);
    //List<AppointmentModel>  myConferenceShow(long number);

    int appointDel( int id);

    PageInfo<TConferenceRoomAppointment> appoints(int page, int size);
}
