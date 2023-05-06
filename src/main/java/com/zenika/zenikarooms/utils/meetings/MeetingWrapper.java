package com.zenika.zenikarooms.utils.meetings;

// In the lingo of constraint Satisfaction algo, a meeting is variable
public class MeetingWrapper {
    private static long generatedMeetings=0; // ids are generated automatically
    private long id;
    //The starting hour of the meeting for simplicity it's an int(Full Hours Only)
    private int startingHour;
    private MeetingType meetingType;
    private int numberOfPeople;

    public MeetingWrapper(int startAt, MeetingType meetingType, int numberOfPeople) {
        this.id=++generatedMeetings; //we'll go with 1 based indexing
        this.startingHour = startAt;
        this.meetingType = meetingType;
        this.numberOfPeople = numberOfPeople;
    }



    public static long getGeneratedMeetings() {
        return generatedMeetings;
    }

    public static void setGeneratedMeetings(long generatedMeetings) {
        MeetingWrapper.generatedMeetings = generatedMeetings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(int startingHour) {
        this.startingHour = startingHour;
    }

    public MeetingType getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(MeetingType meetingType) {
        this.meetingType = meetingType;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

}
