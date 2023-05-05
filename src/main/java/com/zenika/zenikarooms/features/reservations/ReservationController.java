package com.zenika.zenikarooms.features.reservations;

import com.zenika.zenikarooms.features.reservations.dto.NewReservation;
import com.zenika.zenikarooms.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/reservations")
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Response<Reservation> makeReservation(@RequestBody NewReservation newReservation){
        return this.reservationService.makeReservation(newReservation);
    }
    @GetMapping
    public List<Reservation> getReservations(){
        return this.reservationService.getReservations();
    }
}
