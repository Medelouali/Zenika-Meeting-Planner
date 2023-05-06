package com.zenika.zenikarooms.utils.rooms;

import com.zenika.zenikarooms.features.rooms.Room;
import com.zenika.zenikarooms.utils.meetings.*;
import com.zenika.zenikarooms.utils.tools.Tool;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class RoomWrapperTest {

    // The tests cases that corresponds to the isAssignable method
    @Test
    void itShouldAssignAppropriateNumberOfPeopleToRoom() {
        // 70% of 10(7) is <= than to 7
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.TABLEAU, Tool.ECRAN));
        MeetingWrapper meetingWrapper=new MeetingWrapper(9, new RsMeeting(), 7);
        assertThat(roomWrapper.isAssignableToMeeting(meetingWrapper)).isTrue();
    }

    @Test
    void itShouldNotAssignInappropriateNumberOfPeopleToRoom() {
        // 8 is not less <= 70% of 10(7)
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.TABLEAU, Tool.ECRAN));
        MeetingWrapper meetingWrapper=new MeetingWrapper(9, new RsMeeting(), 8);
        assertThat(roomWrapper.isAssignableToMeeting(meetingWrapper)).isFalse();
    }

    // Rs meetings tests
    @Test
    void itShouldNotAssignRsMeetingToRoom() {
        // 3 < 4
        RoomWrapper roomWrapper=new RoomWrapper(3, List.of(Tool.TABLEAU, Tool.ECRAN));
        MeetingWrapper meetingWrapper=new MeetingWrapper(9, new RsMeeting(), 3);
        assertThat(roomWrapper.isAssignableToMeeting(meetingWrapper)).isFalse();
    }

    @Test
    void itShouldAssignRsMeetingToRoom() {
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.TABLEAU, Tool.ECRAN));
        MeetingWrapper meetingWrapper=new MeetingWrapper(9, new RsMeeting(), 3);
        assertThat(roomWrapper.isAssignableToMeeting(meetingWrapper)).isTrue();
    }

    // Rc meetings tests
    @Test
    void itShouldAssignRcMeetingToRoom() { // All Tools and the num of people with capacity are good
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.TABLEAU, Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(9, new RcMeeting(), 3);
        assertThat(roomWrapper.isAssignableToMeeting(meetingWrapper)).isTrue();
    }

    @Test
    void itShouldNotAssignRcMeetingToRoom() { // No Board but num of people with capacity are good
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(9, new RcMeeting(), 3);
        assertThat(roomWrapper.isAssignableToMeeting(meetingWrapper)).isFalse();
    }

    // Spec meetings tests
    @Test
    void itShouldAssignSpecMeetingToRoom() { // All Tools(Only Needs A Board) and the num of people with capacity are good
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.TABLEAU, Tool.ECRAN));
        MeetingWrapper meetingWrapper=new MeetingWrapper(9, new SpecMeeting(), 3);
        assertThat(roomWrapper.isAssignableToMeeting(meetingWrapper)).isTrue();
    }

    @Test
    void itShouldNotAssignSpecMeetingToRoom() { // No Board but num of people with capacity are good
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(9, new SpecMeeting(), 3);
        assertThat(roomWrapper.isAssignableToMeeting(meetingWrapper)).isFalse();
    }

    // Vc tests
    @Test
    void itShouldAssignVcMeetingToRoom() { // All Tools and the num of people with capacity are good
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.WEBCAM, Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(9, new VcMeeting(), 3);
        assertThat(roomWrapper.isAssignableToMeeting(meetingWrapper)).isTrue();
    }

    @Test
    void itShouldNotAssignVcMeetingToRoom() {
        // No Board but num of people with capacity are good
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(9, new VcMeeting(), 3);
        assertThat(roomWrapper.isAssignableToMeeting(meetingWrapper)).isFalse();
    }

    // Now let's test the canMakeReservation method
    @Test
    void itShouldNotAssignVcMeetingToRoomOutOfRange_8h_20h() {
        // All Tools and the num of people with capacity are good but 5AM in the morning is NOT in [8h-20h]
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.WEBCAM, Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(5, new VcMeeting(), 3);
        assertThat(roomWrapper.canMakeReservation(meetingWrapper)).isFalse();
    }

    @Test
    void itShouldAssignVcMeetingToRoomInRange_8h_20h() {
        // All Tools and the num of people with capacity are good but 5AM in the morning is NOT in [8h-20h]
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.WEBCAM, Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(8, new VcMeeting(), 3);
        assertThat(roomWrapper.canMakeReservation(meetingWrapper)).isTrue();
    }

    @Test
    void itShouldNotAssignVcMeetingToRoomIfTheRoomIfOccupied() {
        // The room is already reserved
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.WEBCAM, Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(8, new VcMeeting(), 3);
        roomWrapper.addReservation(meetingWrapper);
        MeetingWrapper meetingWrapper2=new MeetingWrapper(8, new VcMeeting(), 3);
        assertThat(roomWrapper.canMakeReservation(meetingWrapper2)).isFalse();
    }

    @Test
    void itShouldNotAssignVcMeetingToRoomIfTheRoomIfWeAreCleaningIt() {
        // The room should be empty till 10 so it's not available at 9
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.WEBCAM, Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(8, new VcMeeting(), 3);
        roomWrapper.addReservation(meetingWrapper);
        MeetingWrapper meetingWrapper2=new MeetingWrapper(9, new VcMeeting(), 3);
        assertThat(roomWrapper.canMakeReservation(meetingWrapper2)).isFalse();
    }

    @Test
    void itShouldAssignVcMeetingToRoomIfNoOverlappingInTime() {
        // No overlapping in time of reservations
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.WEBCAM, Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(8, new VcMeeting(), 3);
        roomWrapper.addReservation(meetingWrapper);
        MeetingWrapper meetingWrapper2=new MeetingWrapper(10, new VcMeeting(), 3);
        assertThat(roomWrapper.canMakeReservation(meetingWrapper2)).isTrue();
    }

    @Test
    void itShouldNotAssignVcMeetingToRoomInEdgeHour() {
        // meeting from 18h-19h so room would be empty till 20h
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.WEBCAM, Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(18, new VcMeeting(), 3);
        roomWrapper.addReservation(meetingWrapper);
        MeetingWrapper meetingWrapper2=new MeetingWrapper(19, new VcMeeting(), 3);
        assertThat(roomWrapper.canMakeReservation(meetingWrapper2)).isFalse();
    }

    @Test
    void itShouldAssignVcMeetingToRoomAtLastHour() {
        //Meeting in the last hour
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.WEBCAM, Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(19, new VcMeeting(), 3);
        assertThat(roomWrapper.canMakeReservation(meetingWrapper)).isTrue();
    }

    @Test
    void itShouldNotAssignVcMeetingToRoomAtClosingTime_20h() {
        // Meeting when closing at 20h
        RoomWrapper roomWrapper=new RoomWrapper(10, List.of(Tool.WEBCAM, Tool.ECRAN, Tool.PIEUVRE));
        MeetingWrapper meetingWrapper=new MeetingWrapper(20, new VcMeeting(), 3);
        assertThat(roomWrapper.canMakeReservation(meetingWrapper)).isFalse();
    }

}