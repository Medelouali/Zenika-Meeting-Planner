package com.zenika.zenikarooms.algorithms.consraintSatisfaction.meetings;

// In the lingo of constraint Satisfaction algo, a meeting is variable
public class Meeting {
    private static int duration;
    private static long generatedMeetings=0; // ids are generated automatically
    // Ids for meeting, if meeting A is reserved before B, in this case id(A)<id(b)
    // This would help us give priorities to meetings(FIFO)
    private long id;
    //The starting hour of the meeting for simplicity it's an int(Full Hours Only)
    private int startAt;
    private MeetingType meetingType;
    private int numberOfPeople;

    public Meeting(int startAt, MeetingType meetingType, int numberOfPeople) {
        this.id=++generatedMeetings; //we'll go with 1 based indexing
        this.startAt = startAt;
        this.meetingType = meetingType;
        this.numberOfPeople = numberOfPeople;
    }

    public static int getDuration() {
        return duration;
    }

    public static void setDuration(int duration) {
        Meeting.duration = duration;
    }

    public static long getGeneratedMeetings() {
        return generatedMeetings;
    }

    public static void setGeneratedMeetings(long generatedMeetings) {
        Meeting.generatedMeetings = generatedMeetings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
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
