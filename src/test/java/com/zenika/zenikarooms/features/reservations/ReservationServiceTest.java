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

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationServiceTest {
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private ReservationService underTestService;

    @BeforeEach
    void setUp(){
        this.underTestService=new ReservationService(reservationRepository, roomRepository);
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
        List<Room> roomsList = new ArrayList<>();
        for (RoomWrapper room : rooms) {
            Room newRoom = Room.builder()
                    .name(room.getName())
                    .capacity(room.getRealCapacity())
                    .availableTools(room.getAvailableTools())
                    .build();
            roomsList.add(newRoom);
        }
        when(roomRepository.findAll()).thenReturn(roomsList);
    }

    @Test
    @Order(1)
    @Disabled
    void getReservations() {
        // When
        underTestService.getReservations();
        // Then
        // This would make sure that the method findAll was called on reservationRepository get the
        // reservations when making the call above to getReservations
        verify(reservationRepository).findAll();
    }

    // Making a reservation at 2 in the morning
    @Test
    @Order(2)
    @Disabled
    void makeLeftOutRangeReservation() {
        // Given
        NewReservation newReservation=new NewReservation(2, ReservationType.RS, 8);
        // When
        Response<Reservation> result=this.underTestService.makeReservation(newReservation);
        // Then
        assertThat(result.isError()).isTrue();
    }

    @Test
    @Order(3)
    @Disabled
    void makeRightOutRangeReservation() {
        // Given
        NewReservation newReservation=new NewReservation(21, ReservationType.RS, 8);
        // When
        Response<Reservation> result=this.underTestService.makeReservation(newReservation);
        // Then
        assertThat(result.isError()).isTrue();
    }

    @Test
    @Order(4)
    @Disabled
    void makeInRangeReservation() {
        // Given
        NewReservation newReservation=new NewReservation(8, ReservationType.RS, 8);
        // When
        Response<Reservation> result=this.underTestService.makeReservation(newReservation);
        // Then
        assertThat(result.isError()).isFalse();
    }

    // Now let's test the meetings we have in the use case(Monday Morning Reservations)

    @Test
    @Order(5)
    void makeMeeting1Reservation() {
        NewReservation newReservation2 = new NewReservation(9, ReservationType.VC, 6);
        Response<Reservation> result2 = this.underTestService.makeReservation(newReservation2);
        assertThat(result2.isError()).isTrue();
    }

    @Test
    @Order(6)
    void makeMeeting2Reservation() {
        NewReservation newReservation2 = new NewReservation(9, ReservationType.VC, 6);
        Response<Reservation> result2 = this.underTestService.makeReservation(newReservation2);
        assertThat(result2.isError()).isTrue();
    }

    @Test
    @Order(7)
    void makeMeeting3Reservation() {
        NewReservation newReservation3=new NewReservation(11, ReservationType.RC, 4);
        Response<Reservation> result3=this.underTestService.makeReservation(newReservation3);
        assertThat(result3.isError()).isTrue();
    }

    @Test
    @Order(8)
    void makeMeeting4Reservation() {
        NewReservation newReservation4=new NewReservation(11, ReservationType.RS, 2);
        Response<Reservation> result4=this.underTestService.makeReservation(newReservation4);
        assertThat(result4.isError()).isFalse();
    }

    @Test
    @Order(9)
    void makeMeeting5Reservation() {
        NewReservation newReservation5=new NewReservation(11, ReservationType.SPEC, 9);
        Response<Reservation> result5=this.underTestService.makeReservation(newReservation5);
        assertThat(result5.isError()).isTrue();
    }

    @Test
    @Order(10)
    void makeMeeting6Reservation() {
        NewReservation newReservation6=new NewReservation(9, ReservationType.RC, 7);
        Response<Reservation> result6=this.underTestService.makeReservation(newReservation6);
        assertThat(result6.isError()).isTrue();
    }

    @Test
    @Order(11)
    void makeMeeting7Reservation() {
        NewReservation newReservation7 = new NewReservation(8, ReservationType.VC, 9);
        Response<Reservation> result7 = this.underTestService.makeReservation(newReservation7);
        assertThat(result7.isError()).isTrue();
    }

    @Test
    @Order(12)
    void makeMeeting8Reservation() {
        NewReservation newReservation8 = new NewReservation(8, ReservationType.SPEC, 10);
        Response<Reservation> result8 = this.underTestService.makeReservation(newReservation8);
        assertThat(result8.isError()).isFalse();
    }

    @Test
    @Order(13)
    void makeMeeting9Reservation() {
        NewReservation newReservation9 = new NewReservation(9, ReservationType.SPEC, 5);
        Response<Reservation> result9 = this.underTestService.makeReservation(newReservation9);
        assertThat(result9.isError()).isFalse();
    }

    @Test
    @Order(14)
    void makeMeeting10Reservation() {
        NewReservation newReservation10 = new NewReservation(9, ReservationType.RS, 4);
        Response<Reservation> result10 = this.underTestService.makeReservation(newReservation10);
        assertThat(result10.isError()).isFalse();
    }

    @Test
    @Order(15)
    void makeMeeting11Reservation() {
        NewReservation newReservation11=new NewReservation(9, ReservationType.RC, 8);
        Response<Reservation> result11=this.underTestService.makeReservation(newReservation11);
        assertThat(result11.isError()).isTrue();
    }

    @Test
    @Order(16)
    void makeMeeting12Reservation() {
        NewReservation newReservation12=new NewReservation(11, ReservationType.VC, 12);
        Response<Reservation> result12=this.underTestService.makeReservation(newReservation12);
        assertThat(result12.isError()).isTrue();
    }

    @Test
    @Order(17)
    void makeMeeting13Reservation() {
        NewReservation newReservation13 = new NewReservation(11, ReservationType.SPEC, 5);
        Response<Reservation> result13 = this.underTestService.makeReservation(newReservation13);
        assertThat(result13.isError()).isTrue();
    }

    @Test
    @Order(18)
    void makeMeeting14Reservation() {
        NewReservation newReservation14 = new NewReservation(8, ReservationType.VC, 3);
        Response<Reservation> result14 = this.underTestService.makeReservation(newReservation14);
        assertThat(result14.isError()).isTrue();
    }

    @Test
    @Order(19)
    void makeMeeting15Reservation() {
        NewReservation newReservation15 = new NewReservation(8, ReservationType.SPEC, 2);
        Response<Reservation> result15 = this.underTestService.makeReservation(newReservation15);
        assertThat(result15.isError()).isFalse();
    }

    @Test
    @Order(20)
    void makeMeeting16Reservation() {
        NewReservation newReservation16 = new NewReservation(8, ReservationType.VC, 12);
        Response<Reservation> result16 = this.underTestService.makeReservation(newReservation16);
        assertThat(result16.isError()).isTrue();
    }

    @Test
    @Order(21)
    void makeMeeting17Reservation() {
        NewReservation newReservation17 = new NewReservation(10, ReservationType.VC, 6);
        Response<Reservation> result17 = this.underTestService.makeReservation(newReservation17);
        assertThat(result17.isError()).isTrue();
    }

    @Test
    @Order(22)
    void makeMeeting18Reservation() {
        NewReservation newReservation18 = new NewReservation(11, ReservationType.RS, 2);
        Response<Reservation> result18 = this.underTestService.makeReservation(newReservation18);
        assertThat(result18.isError()).isFalse();
    }

    @Test
    @Order(23)
    void makeMeeting19Reservation() {
        NewReservation newReservation19=new NewReservation(9, ReservationType.RS, 4);
        Response<Reservation> result19=this.underTestService.makeReservation(newReservation19);
        assertThat(result19.isError()).isFalse();
    }

    @Test
    @Order(24)
    void makeMeeting20Reservation() {
        NewReservation newReservation20=new NewReservation(9, ReservationType.RC, 7);
        Response<Reservation> result20=this.underTestService.makeReservation(newReservation20);
        assertThat(result20.isError()).isTrue();
    }
}