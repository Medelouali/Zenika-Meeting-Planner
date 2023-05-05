package com.zenika.zenikarooms.utils.rooms;

import com.zenika.zenikarooms.utils.tools.Tool;
import com.zenika.zenikarooms.utils.meetings.MeetingWrapper;

import java.util.ArrayList;
import java.util.List;


public class RoomWrapper {
    // In covid the room supports 70%(fullRation=0.7) of its real capacity
    // but now that covid is over we could just change this ratio to 0.9(90%) or even 1(100%)
    private static double fullRatio=0.7;
    // The room should be empty for one hour before its next use
    // the value of cleaningDuration is 0.5 because if we have 0.5h before and after a reservation
    // we would have an hour between two consecutive reservations
    private static double cleaningDuration=0.5;
    // A meeting could happen only between 8h-20h
    private static int minReservationHour=8;
    private static int maxReservationHour=20;
    private static int reservationDuration=1;
    private static long generatedRooms=0; // ids are generated automatically
    // Ids for meeting, if meeting A is reserved before B, in this case id(A)<id(b)
    // This would help us give priorities to meetings(FIFO)
    // See this in action in the method selectUnassignedVariable of the ConstraintSatisfaction class
    private long id;
    private String name;
    private int realCapacity;
    List<Tool> availableTools;
    List<MeetingWrapper> reservations;

    public RoomWrapper(String name, int realCapacity, List<Tool> availableTools) {
        this.name = name;
        this.id=++generatedRooms;
        this.realCapacity = realCapacity;
        this.availableTools = availableTools;
        this.reservations= new ArrayList<>();
    }

    public RoomWrapper(int realCapacity, List<Tool> availableTools, List<MeetingWrapper> reservations) {
        this.realCapacity = realCapacity;
        this.availableTools = availableTools;
        this.reservations = reservations;
    }

    public RoomWrapper(int realCapacity, List<Tool> availableTools) {
        this.realCapacity = realCapacity;
        this.availableTools = availableTools;
    }

    // Check if we can assign a room to a meeting or not
    // This method is private to demonstrate encapsulation
    private boolean isAssignableToMeeting(MeetingWrapper meeting){
        boolean isAssignable=meeting.getNumberOfPeople()<=(fullRatio*this.realCapacity);
        if(isAssignable){
            if(meeting.getMeetingType().getMustHaveTools().isEmpty()){
                return meeting.getNumberOfPeople() >= 3;
            }
            boolean allIn=true;
            for(Tool tool: meeting.getMeetingType().getMustHaveTools()){
                if(!this.availableTools.contains(tool)){
                    allIn=false;
                    break;
                }
            }
            return allIn;
        }
        return false;
    }

    // This method verifies if we can we make a reservation to a room by checking if the room is already
    // booked between [t1, t2], it returns true if the room is already booked for [t1, t2] or the
    // booking timeframe is out of the range [8h-20h] and returns false otherwise
    public boolean canMakeReservation(MeetingWrapper meeting){
        int meetingDuration= RoomWrapper.reservationDuration;
        // If the room does not have the required tools then we can't make a reservation
        if(!this.isAssignableToMeeting(meeting)) return false;
        // Making sure the timeframe is right
        if(meeting.getStartingHour() < minReservationHour || meetingDuration+meeting.getStartingHour() > maxReservationHour) return false;
        for(MeetingWrapper r: this.reservations){
            // Edge cases:
            if(r.getStartingHour()==minReservationHour &&
                    meeting.getStartingHour()<minReservationHour+meetingDuration+cleaningDuration)return false;
            if(r.getStartingHour()+meetingDuration==maxReservationHour &&
                    meeting.getStartingHour()+meetingDuration>r.getStartingHour()-cleaningDuration)return false;
            // Inner hours i.e: 9->19
            boolean isNoOverlap=(
                    meeting.getStartingHour()+meetingDuration < r.getStartingHour()-cleaningDuration ||
                            meeting.getStartingHour() >  r.getStartingHour()+meetingDuration+cleaningDuration
            );
            if(!isNoOverlap)return false;
            // We can factorize this code using or op but for maintainability and readability
            // we'll leave at that
        }
        return true;
    }

    public long getId() {
        return id;
    }

    public int getRealCapacity() {
        return realCapacity;
    }

    public List<Tool> getAvailableTools() {
        return availableTools;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRealCapacity(int realCapacity) {
        this.realCapacity = realCapacity;
    }

    public void setAvailableTools(List<Tool> availableTools) {
        this.availableTools = availableTools;
    }

    public List<MeetingWrapper> getReservations() {
        return reservations;
    }

    public void setReservations(List<MeetingWrapper> reservations) {
        this.reservations = reservations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
