package com.zenika.zenikarooms.utils.meetings;

import com.zenika.zenikarooms.utils.tools.Tool;

import java.util.ArrayList;

public class RsMeeting implements MeetingType{
    private static ArrayList<Tool> mustHaveTools=new ArrayList<>();

    public ArrayList<Tool> getMustHaveTools() {
        return mustHaveTools;
    }
}
