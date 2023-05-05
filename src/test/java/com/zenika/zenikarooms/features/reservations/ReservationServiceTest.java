package com.zenika.zenikarooms.features.reservations;

import com.zenika.zenikarooms.features.reservations.dto.NewReservation;
import com.zenika.zenikarooms.features.reservations.dto.ReservationType;
import com.zenika.zenikarooms.features.rooms.Room;
import com.zenika.zenikarooms.features.rooms.RoomRepository;
import com.zenika.zenikarooms.features.rooms.RoomService;
import com.zenika.zenikarooms.utils.response.Response;
import com.zenika.zenikarooms.utils.rooms.RoomWrapper;
import com.zenika.zenikarooms.utils.tools.Tool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomService roomService;// we need this to generate the h2 in-memory rooms in the setup for reservations
    private ReservationService underTestService;

    @BeforeEach
    void setUp(){
        this.underTestService=new ReservationService(reservationRepository, roomRepository);
        roomService=new RoomService(roomRepository);
        RoomRepository roomRepository = mock(RoomRepository.class);
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
        List<Room> roomsList=new ArrayList<>();
        for (RoomWrapper room: rooms) {
            Room newRoom=Room.builder().
                    name(room.getName())
                    .capacity(room.getRealCapacity()).availableTools(room.getAvailableTools())
                    .build();
            roomsList.add(newRoom);
            roomRepository.save(newRoom);
        };
//        when(roomRepository.findAll()).thenReturn(roomsList);
    }


    @Test
    void getReservations() {
        // When
        underTestService.getReservations();
        // Then
        // This would make sure that the method findAll was called on reservationRepository get the
        // reservations when making the call above to getReservations
        verify(reservationRepository).findAll();
    }

    @Test
    void makeReservation() {
        // Given
        NewReservation newReservation=new NewReservation(9, ReservationType.VC, 8);
        // When
        Response<Reservation> result=this.underTestService.makeReservation(newReservation);
        // Then
        assertThat(result.isError()).isFalse();
    }
}