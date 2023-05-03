package com.zenika.zenikarooms.algorithms.consraintSatisfaction.meetings;

import com.zenika.zenikarooms.algorithms.consraintSatisfaction.tools.Tool;

import java.util.ArrayList;

public class RcMeeting implements MeetingType{
    private static ArrayList<Tool> mustHaveTools=new ArrayList<>();
    {
        mustHaveTools.add(Tool.TABLEAU);
        mustHaveTools.add(Tool.ECRAN);
        mustHaveTools.add(Tool.PIEUVRE);
    }

    public ArrayList<Tool> getMustHaveTools() {
        return mustHaveTools;
    }
}