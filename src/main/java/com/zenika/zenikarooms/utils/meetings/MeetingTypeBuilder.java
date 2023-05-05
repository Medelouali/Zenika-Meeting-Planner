package com.zenika.zenikarooms.utils.meetings;

import com.zenika.zenikarooms.features.reservations.dto.ReservationType;

public class MeetingTypeBuilder {

    public static MeetingType getMeetingTypeFromReservationType(ReservationType reservationType){
        if(reservationType==ReservationType.VC)return new VcMeeting();
        if(reservationType==ReservationType.RS)return new RsMeeting();
        if(reservationType==ReservationType.SPEC)return new SpecMeeting();
        return new RcMeeting();
    }
}
