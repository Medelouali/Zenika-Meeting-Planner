package com.zenika.zenikarooms.features.reservations;


import com.zenika.zenikarooms.features.reservations.dto.NewReservation;
import com.zenika.zenikarooms.features.reservations.dto.ReservationType;
import com.zenika.zenikarooms.features.rooms.Room;
import com.zenika.zenikarooms.features.rooms.RoomRepository;
import com.zenika.zenikarooms.utils.response.Response;
import com.zenika.zenikarooms.utils.rooms.RoomWrapper;
import com.zenika.zenikarooms.utils.tools.Tool;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
 * In this test class we test the reservations in dependence with each other and in order ie:
 * if test # n reserves a room and test # n+1 has satisfies the conditions to reserve it,
 * the room would NOT be reserved in the class ReservationServiceNoOrder
 * we'll deal with the independence between reservations
*/

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationServiceWithOrderTest {
    private static List<Room> roomsList=new ArrayList<>();
    private List<Reservation> reservationList=new ArrayList<>();
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private ReservationService underTestService;

    @BeforeAll
    static void beforeAll() {
        List<RoomWrapper> rooms = Arrays.asList(
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
        roomsList = new ArrayList<>();
        for (RoomWrapper room : rooms) {
            Room newRoom = Room.builder()
                    .name(room.getName())
                    .capacity(room.getRealCapacity())
                    .availableTools(room.getAvailableTools())
                    .build();
            roomsList.add(newRoom);
        }
    }

    @BeforeEach
    public void setUp() {
        when(roomRepository.findAll()).thenReturn(roomsList);
        when(reservationRepository.findAll()).thenReturn(reservationList);
    }

    // Now let's test the meetings we have in the use case(Monday Morning Reservations)
    @Test
    @Order(1)
    void itShouldMake8FirstReservationsFromUseCase() {
        // Testing from meeting 1 to 8 in the use case
        NewReservation newReservation1 = new NewReservation(9, ReservationType.VC, 8);
        Response<Reservation> result1 = this.underTestService.makeReservation(newReservation1);
        Reservation res1=result1.getData();
        res1.setRoom(roomsList.get(0)); // the algo is supposed to assign this
        // meeting to room E1001 # 1, but with 0 based indexing it's # 0
        reservationList.add(res1);
        assertThat(result1.isError()).isFalse();

        NewReservation newReservation2 = new NewReservation(9, ReservationType.VC, 6);
        Response<Reservation> result2 = this.underTestService.makeReservation(newReservation2);
        assertThat(result2.isError()).isTrue(); // It failed cuz room

        NewReservation newReservation3=new NewReservation(11, ReservationType.RC, 4);
        Response<Reservation> result3=this.underTestService.makeReservation(newReservation3);
        assertThat(result3.isError()).isTrue(); // No room supporting Rc meetings

        NewReservation newReservation4=new NewReservation(11, ReservationType.RS, 2);
        Response<Reservation> result4=this.underTestService.makeReservation(newReservation4);
        Reservation res4=result4.getData();
        res4.setRoom(roomsList.get(2));
        reservationList.add(res4);
        assertThat(result4.isError()).isFalse();

        NewReservation newReservation5=new NewReservation(11, ReservationType.SPEC, 9);
        Response<Reservation> result5=this.underTestService.makeReservation(newReservation5);
        assertThat(result5.isError()).isTrue(); // No room has the cap for 9 people supporting Spec meetings

        NewReservation newReservation6=new NewReservation(9, ReservationType.RC, 7);
        Response<Reservation> result6=this.underTestService.makeReservation(newReservation6);
        assertThat(result6.isError()).isTrue(); // No room supports Rc meetings

        NewReservation newReservation7 = new NewReservation(8, ReservationType.VC, 9);
        Response<Reservation> result7 = this.underTestService.makeReservation(newReservation7);
        assertThat(result7.isError()).isTrue();

        NewReservation newReservation8 = new NewReservation(8, ReservationType.SPEC, 10);
        Response<Reservation> result8 = this.underTestService.makeReservation(newReservation8);
        assertThat(result8.isError()).isTrue(); // No room has the cap for 10 people supporting Spec meetings

    }

}
