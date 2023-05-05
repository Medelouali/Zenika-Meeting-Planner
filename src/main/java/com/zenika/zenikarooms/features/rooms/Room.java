package com.zenika.zenikarooms.features.rooms;

import com.zenika.zenikarooms.utils.tools.Tool;
import com.zenika.zenikarooms.features.reservations.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Room {
    @Id
    @SequenceGenerator(
            name="room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    private Long id;
    private String name;
    private int capacity;

    @ElementCollection(targetClass = Tool.class)
    @Enumerated(EnumType.STRING)
    private List<Tool> availableTools= new ArrayList<>();




}
