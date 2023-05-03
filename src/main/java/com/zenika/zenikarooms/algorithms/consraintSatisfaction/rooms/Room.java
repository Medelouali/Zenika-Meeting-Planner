package com.zenika.zenikarooms.algorithms.consraintSatisfaction.rooms;

import com.zenika.zenikarooms.algorithms.consraintSatisfaction.tools.Tool;
import com.zenika.zenikarooms.algorithms.consraintSatisfaction.meetings.Meeting;

import java.util.ArrayList;

public class Room {
    private static long generatedRooms=0; // ids are generated automatically
    // Ids for meeting, if meeting A is reserved before B, in this case id(A)<id(b)
    // This would help us give priorities to meetings(FIFO)
    private long id;
    private int realCapacity;
    ArrayList<Tool> availableTools;

    public Room(int realCapacity, ArrayList<Tool> availableTools) {
        this.id=++generatedRooms;
        this.realCapacity = realCapacity;
        this.availableTools = availableTools;
    }

    // Check if we can assign a room to a meeting or not
    public boolean isAssignableToMeeting(Meeting meeting){
        boolean isAssignable=meeting.getNumberOfPeople()<=((70/100)*this.realCapacity);
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
}
