package com.zenika.zenikarooms.algorithms.consraintSatisfaction.algo;

import com.zenika.zenikarooms.algorithms.consraintSatisfaction.algo.domainsBuilder.DomainsBuilder;
import com.zenika.zenikarooms.algorithms.consraintSatisfaction.meetings.Meeting;
import com.zenika.zenikarooms.algorithms.consraintSatisfaction.rooms.Reservation;
import com.zenika.zenikarooms.algorithms.consraintSatisfaction.rooms.Room;

import java.util.*;

public class ConstraintSatisfactionAlgo {
    // This method matches meetings to rooms based on the Constraint Satisfaction algorithm
    public Map<Meeting, Room> solve(List<Meeting> meetings, List<Room> rooms){
        //Building the domains for the variables(Meetings)
        Map<Meeting, List<Room>> domains=new DomainsBuilder().build(meetings, rooms);
        // Solve the problem using backtracking
        return solve(domains);
    }
    // The rest of the methods are private to respect the encapsulation principle
    // We are only exposing a simple entry point to the algo (the solve method above)
    // This makes using the algo easy
    private Map<Meeting, Room> solve(Map<Meeting, List<Room>> domains) {
        // Initialize the assignment and the set of unassigned domains
        Map<Meeting, Room> assignments = new HashMap<>();
        Set<Meeting> unassigned = new HashSet<>(domains.keySet());

        // Call the recursive helper function to find a solution
        return solveHelper(domains, assignments, unassigned);
    }

    private Map<Meeting, Room> solveHelper(Map<Meeting, List<Room>> domains, Map<Meeting, Room> assignments, Set<Meeting> unassigned) {
        // Base case: if all meetings are assigned, the problem is solved
        if (unassigned.isEmpty()) {
            return assignments;
        }

        // Choose a variable to assign
        Meeting meeting = selectUnassignedVariable(unassigned);

        // Try all values in the variable(meeting)'s domain
        for (Room room : domains.get(meeting)) {
            // Check if the value satisfies the constraints with the assigned meeting
            if (isConsistent(meeting, room, assignments)) {
                // If the value is consistent, add it to the assignment and continue recursively
                assignments.put(meeting, room);
                unassigned.remove(meeting);
                Map<Meeting, Room> result = solveHelper(domains, assignments, unassigned);
                if (result != null) {
                    // If a solution was found, return it
                    return result;
                }
                // If no solution was found, backtrack by removing the value from the assignment and
                // then we continue with the next value
                assignments.remove(meeting);
                unassigned.add(meeting);
            }
        }

        // If no value in the domain satisfies the constraints, backtrack by returning null
        return null;
    }

    private Meeting selectUnassignedVariable(Set<Meeting> unassigned) {
        // We are selecting the meeting with the smallest id because we are respecting
        // the order in which the reservations came in to our system, first reserved, first served(FIFO)
        long minId = Long.MAX_VALUE;
        Meeting meetingWithSmallestId = null;
        for (Meeting meeting : unassigned) {
            long meetingId = meeting.getId();
            if (meetingId < minId) {
                minId = meetingId;
                meetingWithSmallestId = meeting;
            }
        }
        return meetingWithSmallestId;
    }

    private boolean isConsistent(Meeting meeting, Room room, Map<Meeting, Room> assignments) {
        // Check if the value is consistent with the already assigned meetings
        // But in our case we have no such constraints
        if(room.canMakeReservation(meeting)){
            assignments.put(meeting, room);
            return true;
        };
        return false;
    }
}
