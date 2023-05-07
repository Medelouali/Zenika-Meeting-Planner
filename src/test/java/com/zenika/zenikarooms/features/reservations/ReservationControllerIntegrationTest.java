package com.zenika.zenikarooms.features.reservations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenika.zenikarooms.features.reservations.dto.NewReservation;
import com.zenika.zenikarooms.features.reservations.dto.ReservationType;
import com.zenika.zenikarooms.features.rooms.RoomService;
import com.zenika.zenikarooms.utils.meetings.VcMeeting;
import com.zenika.zenikarooms.utils.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class ReservationControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;
    @MockBean
    private RoomService roomService;

    @Test
    void itShouldCheckIfTheEndpointIsAccessible() throws Exception {
        NewReservation newReservation = new NewReservation(9, ReservationType.VC, 8);
        String json = new ObjectMapper().writeValueAsString(newReservation);

        mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void itShouldMakeReservation() throws Exception {
        NewReservation newReservation = new NewReservation(9, ReservationType.VC, 8);
        String json = new ObjectMapper().writeValueAsString(newReservation);

        mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startingHour").value(9));
    }
}