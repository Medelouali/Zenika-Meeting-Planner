package com.zenika.zenikarooms.algorithms.consraintSatisfaction.algo.domainsBuilder;

import com.zenika.zenikarooms.algorithms.consraintSatisfaction.meetings.Meeting;
import com.zenika.zenikarooms.algorithms.consraintSatisfaction.rooms.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// In the constraint Satis lingo, a variable(meeting in our case) has a set of values(rooms in our case)
// that can have, so this class is responsible for building the domain for each meeting
// The algo takes as input these domains(for backtracking)
public class DomainsBuilder {
    // This returns all possible rooms that the meeting can be at within a specific hour
    public Map<Meeting, List<Room>> build(List<Meeting> meetings, List<Room> rooms){
        Map<Meeting, List<Room>> meetingToCandidateRooms= new HashMap<>();
        for(Meeting meeting : meetings){
            List<Room> verifiedRoomsList=new ArrayList<>();
            meetingToCandidateRooms.put(meeting, verifiedRoomsList);
            for(Room room : rooms){
                if(room.isAssignableToMeeting(meeting)){
                    verifiedRoomsList.add(room);
                }
            };
            meetingToCandidateRooms.put(meeting, verifiedRoomsList);
        }
        return meetingToCandidateRooms;
    }
}
