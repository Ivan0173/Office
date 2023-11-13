package com.ssau.reserv.service.implementation;

import com.ssau.reserv.dto.ReservationPojo;
import com.ssau.reserv.entity.Office;
import com.ssau.reserv.entity.Reservation;
import com.ssau.reserv.exeption.OfficeNotFoundException;
import com.ssau.reserv.exeption.ReservationNotFoundException;
import com.ssau.reserv.repository.OfficeRepository;
import com.ssau.reserv.repository.ReservationRepository;
import com.ssau.reserv.repository.UserRepository;
import com.ssau.reserv.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final OfficeRepository officeRepository;

    private final UserRepository userRepository;

    @Override
    public ReservationPojo add(ReservationPojo reservationPojo) throws OfficeNotFoundException {
        if(reservationPojo.getFinishDate().before(reservationPojo.getStartDate()))
            throw new OfficeNotFoundException("Время начала больше времени окончания");
        List<Reservation> reservationList=reservationRepository.findAll();
        boolean timeIsBusy = false;
        boolean startInterval = false;
        boolean finishInterval = false;
        for(Reservation reservation1: reservationList) {
            startInterval=(!reservationPojo.getStartDate().before(reservation1.getStartDate()))&&
                    (!reservation1.getFinishDate().before(reservationPojo.getStartDate()));
            finishInterval=(!reservationPojo.getFinishDate().before(reservation1.getStartDate())&&
                    !reservation1.getFinishDate().before(reservationPojo.getFinishDate()));
            if((reservationPojo.getOfficeName().equals(reservation1.getOffice().getName())&&
                    reservationPojo.getCabinet().equals(reservation1.getOffice().getCabinet())))
                if(startInterval||finishInterval)
                    throw new OfficeNotFoundException("Это время занято");
        }
        Reservation reservation = reservationPojo.toEntity();
        reservation.setName("12");
        boolean officeFlag=false;
        List<Office> officeList = officeRepository.findAll();
        for (Office office: officeList) {
            if (office.getName().equals(reservationPojo.getOfficeName()) && office.getCabinet().equals(reservationPojo.getCabinet())) {
                reservation.setOffice(office);
                officeFlag=true;
            }
        }
        if(!officeFlag)
            throw new OfficeNotFoundException("Такого офиса нет");
        reservation.setUser(
                userRepository.findGroupByName(reservationPojo.getUserName())
                        .orElseThrow(
                                () -> new OfficeNotFoundException("User with name= " + reservationPojo.getOfficeName() + " not found!")
                        )
        );
        return ReservationPojo.fromEntity(reservationRepository.save(reservation));
    }

    @Override
    public Optional<ReservationPojo> getById(long id) {
        return reservationRepository.findById(id).map(ReservationPojo::fromEntity);
    }

    @Override
    public ReservationPojo update(ReservationPojo reservationPojo) throws ReservationNotFoundException, OfficeNotFoundException {
        if(!reservationRepository.existsById(reservationPojo.getId()))
            throw new ReservationNotFoundException("Reservation with id= " + reservationPojo.getId() + " not found!");
        return add(reservationPojo);
    }

    @Override
    public boolean deleteById(long id) {
        if(reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }else
            return false;
    }

    @Override
    public long count() {
        return reservationRepository.count();
    }

    @Override
    public List<ReservationPojo> findAll(){
        List<Reservation> reservations=reservationRepository.findAll();
        return reservations
                .stream()
                .map(ReservationPojo::fromEntity)
                .toList();
    }
}
