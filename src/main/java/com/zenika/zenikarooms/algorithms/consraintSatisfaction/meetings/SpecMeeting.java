package com.zenika.zenikarooms.algorithms.consraintSatisfaction.meetings;

import com.zenika.zenikarooms.algorithms.consraintSatisfaction.tools.Tool;

import java.util.ArrayList;

public class SpecMeeting implements MeetingType{
    private static ArrayList<Tool> mustHaveTools=new ArrayList<>();
    {
        mustHaveTools.add(Tool.TABLEAU);
    }

    public ArrayList<Tool> getMustHaveTools() {
        return mustHaveTools;
    }
}
