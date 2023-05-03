package com.zenika.zenikarooms.algorithms.consraintSatisfaction.meetings;

import com.zenika.zenikarooms.algorithms.consraintSatisfaction.tools.Tool;

import java.util.ArrayList;

public class RsMeeting implements MeetingType{
    private static ArrayList<Tool> mustHaveTools=new ArrayList<>();

    public ArrayList<Tool> getMustHaveTools() {
        return mustHaveTools;
    }
}
