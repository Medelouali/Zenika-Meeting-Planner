package com.zenika.zenikarooms.utils.meetings;

import com.zenika.zenikarooms.utils.tools.Tool;

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
