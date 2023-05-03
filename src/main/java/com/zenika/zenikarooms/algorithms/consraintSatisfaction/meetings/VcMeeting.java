package com.zenika.zenikarooms.algorithms.consraintSatisfaction.meetings;

import com.zenika.zenikarooms.algorithms.consraintSatisfaction.tools.Tool;

import java.util.ArrayList;

public class VcMeeting implements MeetingType{
    private static ArrayList<Tool> mustHaveTools=new ArrayList<>();
    {
        mustHaveTools.add(Tool.ECRAN);
        mustHaveTools.add(Tool.PIEUVRE);
        mustHaveTools.add(Tool.WEBCAM);
    }

    public ArrayList<Tool> getMustHaveTools() {
        return mustHaveTools;
    }
}
