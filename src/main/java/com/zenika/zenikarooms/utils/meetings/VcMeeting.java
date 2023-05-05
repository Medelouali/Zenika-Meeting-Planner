package com.zenika.zenikarooms.utils.meetings;

import com.zenika.zenikarooms.utils.tools.Tool;

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
