package com.zenika.zenikarooms.features.reservations;

import com.zenika.zenikarooms.features.reservations.dto.NewReservation;
import com.zenika.zenikarooms.features.rooms.Room;
import com.zenika.zenikarooms.features.rooms.RoomRepository;
import com.zenika.zenikarooms.utils.meetings.MeetingTypeBuilder;
import com.zenika.zenikarooms.utils.meetings.MeetingWrapper;
import com.zenika.zenikarooms.utils.response.Response;
import com.zenika.zenikarooms.utils.rooms.RoomWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }


    public List<Reservation> getReservations(){
        return this.reservationRepository.findAll();
    }

    public Response<Reservation> makeReservation(NewReservation newReservation) {
        // Do the login here to find the appropriate room
        Room room = this.findAppropriateRoom(newReservation);
        if(room==null)return new Response<>(
                true,
                "We are booked right now for "+newReservation.getReservationType()
                        +" meetings and we are totally sorry try with another type (RS, RC, SPEC or VC)",
                null
        );
        // And here make the reservation
        Reservation reservation = Reservation.builder()
                .room(room)
                .startingHour(newReservation.getStartingHour())
                .numberOfPeople(newReservation.getNumOfPeople())
                .reservationType(newReservation.getReservationType())
                .build();

        reservationRepository.save(reservation);
        return new Response<>(
                false,
                "Your reservation for room " + room.getName() +
                        " has been registered successfully, we are waiting for you;)",
                null
        );
    }

    // To find the appropriate room we gotta minimize the gap between the actual number of people in a meeting
    // of the type X(VC, SPEC, RS, RC) and the real capacity of the room that supports that exact meeting type
    private Room findAppropriateRoom(NewReservation reservation){
        Room minGapRoom=null;
        int gap=Integer.MAX_VALUE;

        List<Room> rooms=this.roomRepository.findAll();
        List<Reservation> reservations=this.reservationRepository.findAll();
        for (Room room: rooms) {
            List<MeetingWrapper> currentReservations=new ArrayList<>();
            // Get the already made reservations for the current room
            for(Reservation r: reservations){
                if(r.getRoom().getId()==room.getId()){
                    currentReservations.add(new MeetingWrapper(r.getStartingHour(),
                            MeetingTypeBuilder.getMeetingTypeFromReservationType(r.getReservationType()),
                            r.getNumberOfPeople()));
                }
            }
            RoomWrapper roomWrapper=new RoomWrapper(room.getCapacity(),
                    room.getAvailableTools(), currentReservations);
            // The core login happens inside the canMakeReservation method in RoomWrapper class
            if(
                    roomWrapper.canMakeReservation(
                            new MeetingWrapper(reservation.getStartingHour(),
                                    MeetingTypeBuilder.getMeetingTypeFromReservationType(
                                            reservation.getReservationType()
                                    ),
                                    reservation.getNumOfPeople()))
            ){
                int newGap=(int)(RoomWrapper.getFullRatio()*room.getCapacity()) - reservation.getNumOfPeople();
                if(gap>newGap){
                    gap=newGap;
                    minGapRoom=room;
                }
            };
        }
        return minGapRoom;
    }
}
