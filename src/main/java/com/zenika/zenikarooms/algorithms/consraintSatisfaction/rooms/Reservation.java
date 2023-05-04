package com.zenika.zenikarooms.algorithms.consraintSatisfaction.rooms;

public class Reservation {
    private int startingHour;
    private int endingHour;

    public Reservation(int startingHour, int endingHour) {
        this.startingHour = startingHour;
        this.endingHour = endingHour;
    }

    public int getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(int startingHour) {
        this.startingHour = startingHour;
    }

    public int getEndingHour() {
        return endingHour;
    }

    public void setEndingHour(int endingHour) {
        this.endingHour = endingHour;
    }
}
