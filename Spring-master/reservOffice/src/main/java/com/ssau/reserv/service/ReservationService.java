package com.ssau.reserv.service;

import com.ssau.reserv.dto.ReservationPojo;
import com.ssau.reserv.exeption.OfficeNotFoundException;
import com.ssau.reserv.exeption.ReservationNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    ReservationPojo add(ReservationPojo reservationPojo) throws OfficeNotFoundException;

    Optional<ReservationPojo> getById(long id);

    ReservationPojo update(ReservationPojo reservationPojo) throws OfficeNotFoundException, ReservationNotFoundException;

    boolean deleteById(long id);

    long count();

    List<ReservationPojo> findAll();

    //List<ReservationPojo> findAllByName(String name);
}
