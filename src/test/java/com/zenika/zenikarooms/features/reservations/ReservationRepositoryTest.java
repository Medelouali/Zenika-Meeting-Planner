package com.zenika.zenikarooms.features.reservations;

import com.zenika.zenikarooms.features.rooms.Room;
import com.zenika.zenikarooms.features.rooms.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;
@DataJpaTest
class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    // This test is to make sure the connection to the h2 memory is good
    @Test
    void itShouldCheckReservationById(){
        Room newRoom=Room.builder().
                name("E1002")
                .capacity(20)
                .build();
        Room result=roomRepository.save(newRoom);
        // Then
        assertThat(result.getCapacity()).isEqualTo(20);
    }
}