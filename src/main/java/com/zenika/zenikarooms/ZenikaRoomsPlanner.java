package com.zenika.zenikarooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZenikaRoomsPlanner {

	public static void main(String[] args) {
//		testingAlgo();
		SpringApplication.run(ZenikaRoomsPlanner.class, args);
	}

	public static void testingAlgo(){
		//Generating Rooms
//		List<RoomWrapper> rooms=new ArrayList<>();
//		rooms.add(new RoomWrapper("E1001", 23, Arrays.asList(Tool.NEANT)));
//		rooms.add(new RoomWrapper("E1002", 10, Arrays.asList(Tool.ECRAN)));
//		rooms.add(new RoomWrapper("E1003", 8, Arrays.asList(Tool.PIEUVRE)));
//		rooms.add(new RoomWrapper("E1004", 4, Arrays.asList(Tool.TABLEAU)));
//
//		rooms.add(new RoomWrapper("E2001", 4, Arrays.asList(Tool.NEANT)));
//		rooms.add(new RoomWrapper("E2002", 15, Arrays.asList(Tool.ECRAN, Tool.WEBCAM)));
//		rooms.add(new RoomWrapper("E2003", 7, Arrays.asList(Tool.NEANT)));
//		rooms.add(new RoomWrapper("E2004", 9, Arrays.asList(Tool.TABLEAU)));
//
//		rooms.add(new RoomWrapper("E3001", 13, Arrays.asList(Tool.ECRAN, Tool.WEBCAM, Tool.PIEUVRE)));
//		rooms.add(new RoomWrapper("E3002", 8, Arrays.asList(Tool.NEANT)));
//		rooms.add(new RoomWrapper("E3003", 9, Arrays.asList(Tool.ECRAN, Tool.PIEUVRE)));
//		rooms.add(new RoomWrapper("E3004", 4, Arrays.asList(Tool.NEANT)));
//
//		//Generating Meetings
//		List<MeetingWrapper> meetings=new ArrayList<>();
//		meetings.add(new MeetingWrapper(9, new VcMeeting(), 8));
//		meetings.add(new MeetingWrapper(9, new VcMeeting(), 6));
//		meetings.add(new Meeting(11, new RcMeeting(), 4));
//		meetings.add(new Meeting(11, new RsMeeting(), 2));
//		meetings.add(new Meeting(11, new SpecMeeting(), 9));
//
//		meetings.add(new Meeting(9, new RcMeeting(), 7));
//		meetings.add(new Meeting(8, new VcMeeting(), 9));
//		meetings.add(new Meeting(8, new SpecMeeting(), 10));
//		meetings.add(new Meeting(9, new SpecMeeting(), 5));
//		meetings.add(new Meeting(9, new RsMeeting(), 4));

//		meetings.add(new Meeting(9, new RcMeeting(), 8));
//		meetings.add(new Meeting(11, new VcMeeting(), 12));
//		meetings.add(new Meeting(11, new SpecMeeting(), 5));
//		meetings.add(new Meeting(8, new VcMeeting(), 3));
//		meetings.add(new Meeting(8, new SpecMeeting(), 2));
//
//		meetings.add(new Meeting(8, new VcMeeting(), 12));
//		meetings.add(new Meeting(10, new VcMeeting(), 6));
//		meetings.add(new Meeting(11, new RsMeeting(), 2));
//		meetings.add(new Meeting(9, new RsMeeting(), 4));
//		meetings.add(new Meeting(9, new RcMeeting(), 7));

//		Map<MeetingWrapper, RoomWrapper> solution=new ConstraintSatisfactionAlgo().solve(meetings, rooms);
//		if(solution==null){
//			System.out.println("Solution Not Found");
//		}else{
//			System.out.println("Yes we did it");
//			System.out.println(solution.isEmpty());
//			for (Map.Entry<MeetingWrapper, RoomWrapper> entry : solution.entrySet()) {
//				System.out.println("Meeting # "+entry.getKey().getId()
//						+" will be at room"+entry.getValue().getName()+"\n");
//			}
//		}
	}
}
