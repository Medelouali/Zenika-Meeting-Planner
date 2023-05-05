package com.zenika.zenikarooms.features.rooms;

import com.zenika.zenikarooms.utils.tools.Tool;
import com.zenika.zenikarooms.utils.rooms.RoomWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> generateRooms() {
        //Generating Rooms
        List<RoomWrapper> rooms=Arrays.asList(
                new RoomWrapper("E1001", 23, Arrays.asList(Tool.NEANT)),
            new RoomWrapper("E1002", 10, Arrays.asList(Tool.ECRAN)),
            new RoomWrapper("E1003", 8, Arrays.asList(Tool.PIEUVRE)),
            new RoomWrapper("E1004", 4, Arrays.asList(Tool.TABLEAU)),

            new RoomWrapper("E2001", 4, Arrays.asList(Tool.NEANT)),
            new RoomWrapper("E2002", 15, Arrays.asList(Tool.ECRAN, Tool.WEBCAM)),
            new RoomWrapper("E2003", 7, Arrays.asList(Tool.NEANT)),
            new RoomWrapper("E2004", 9, Arrays.asList(Tool.TABLEAU)),

            new RoomWrapper("E3001", 13, Arrays.asList(Tool.ECRAN, Tool.WEBCAM, Tool.PIEUVRE)),
            new RoomWrapper("E3002", 8, Arrays.asList(Tool.NEANT)),
            new RoomWrapper("E3003", 9, Arrays.asList(Tool.ECRAN, Tool.PIEUVRE)),
            new RoomWrapper("E3004", 4, Arrays.asList(Tool.NEANT))
        );

        for (RoomWrapper room: rooms) {
            Room newRoom=Room.builder().
                    name(room.getName())
                    .capacity(room.getRealCapacity()).availableTools(room.getAvailableTools())
                    .build();
            this.roomRepository.save(newRoom);
        };
        return this.roomRepository.findAll();
    }

    public List<Room> getRooms() {
        return this.roomRepository.findAll();
    }
}
