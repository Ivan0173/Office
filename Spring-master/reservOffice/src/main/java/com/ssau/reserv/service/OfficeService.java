package com.ssau.reserv.service;

import com.ssau.reserv.dto.OfficePojo;
import com.ssau.reserv.exeption.OfficeAlreadyExistsException;
import com.ssau.reserv.exeption.OfficeNotFoundException;
import com.ssau.reserv.exeption.ReservationNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OfficeService {

    OfficePojo add(OfficePojo officePojo) throws ReservationNotFoundException, OfficeAlreadyExistsException;

    Optional<OfficePojo> getById(long id);

    Optional<OfficePojo> getByName(String name);

    OfficePojo update(OfficePojo officePojo) throws OfficeNotFoundException, ReservationNotFoundException, OfficeAlreadyExistsException;

    boolean deleteById(long id);

    long count();

    List<OfficePojo> findAll();
}
