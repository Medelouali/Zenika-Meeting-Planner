package com.zenika.zenikarooms.utils.meetings;

import com.zenika.zenikarooms.utils.tools.Tool;

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
