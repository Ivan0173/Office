package com.ssau.reserv.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "offices")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String cabinet;

    private boolean projector;

    private boolean board;

    private int numberSeats;
    @OrderBy(value = "name")
    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations = new LinkedList<>();
}
