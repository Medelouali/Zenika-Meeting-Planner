package com.zenika.zenikarooms.algorithms.consraintSatisfaction.rooms;

import com.zenika.zenikarooms.algorithms.consraintSatisfaction.tools.Tool;
import com.zenika.zenikarooms.algorithms.consraintSatisfaction.meetings.Meeting;

import java.util.Arrays;
import java.util.List;

public class Room {
    // In covid the room supports 70%(fullRation=0.7) of its real capacity
    // but now that covid is over we could just change this ratio to 0.9(90%) or even 1(100%)
    private static double fullRatio=0.7;
    private static int cleaningDuration=1; // The room should be empty for one hour before its next use
    private static long generatedRooms=0; // ids are generated automatically
    // Ids for meeting, if meeting A is reserved before B, in this case id(A)<id(b)
    // This would help us give priorities to meetings(FIFO)
    // See this in action in the method selectUnassignedVariable of the ConstraintSatisfaction class
    private long id;
    private String name;
    private int realCapacity;
    List<Tool> availableTools;
    List<Reservation> reservations;

    public Room(String name, int realCapacity, List<Tool> availableTools) {
        this.name = name;
        this.id=++generatedRooms;
        this.realCapacity = realCapacity;
        this.availableTools = availableTools;
        this.reservations= Arrays.asList();
    }

    // Check if we can assign a room to a meeting or not
    public boolean isAssignableToMeeting(Meeting meeting){
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
    public boolean canMakeReservation(Meeting meeting){
        int meetingDuration=Meeting.getDuration();
        // If the room does not have the required tools then we can't make a reservation
        if(!this.isAssignableToMeeting(meeting)) return false;
        // Making sure the the timeframe is right
        if(meeting.getStartsAt() < 8 || meetingDuration+meeting.getStartsAt() > 20) return false;
        for(Reservation r: this.reservations){
            // Edge cases:
            if(r.getStartingHour()==8 && meeting.getStartsAt()<10)return false;
            if(r.getEndingHour()==20 && meetingDuration+meeting.getStartsAt()>18)return false;
            // Inner hours i.e: 9->19
            if(meetingDuration+meeting.getStartsAt() > r.getStartingHour()-cleaningDuration)return false;
            if(meeting.getStartsAt()<r.getEndingHour()+cleaningDuration)return false;
            // We can factorize this code using or op but for maintainability and readability
            // we'll leave at that
        }
//        this.reservations.add(reservation);
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
