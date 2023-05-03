package com.zenika.zenikarooms.algorithms.consraintSatisfaction.algo;

import com.zenika.zenikarooms.algorithms.consraintSatisfaction.algo.domainsBuilder.DomainsBuilder;
import com.zenika.zenikarooms.algorithms.consraintSatisfaction.meetings.Meeting;
import com.zenika.zenikarooms.algorithms.consraintSatisfaction.rooms.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConstraintSatisfactionAlgo {
    // This method matches meetings to rooms based on the Constraint Satis algorithm
    public Map<Meeting, Room> getMapping(ArrayList<Meeting> meetings, ArrayList<Room> rooms){
        Map<Meeting, Room> meetingsToRooms = new HashMap<>();

        //Building the domains for the variables(Meetings)
        Map<Meeting, ArrayList<Room>> domains=new DomainsBuilder().build(meetings, rooms);


        return meetingsToRooms;
    }
}
