package com.ssau.reserv.dto;

import com.ssau.reserv.entity.Reservation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Getter @Setter @ToString
public class ReservationPojo {
    private long id;
    private String name;
    private Date startDate;
    private Date finishDate;
    private String officeName;
    private String cabinet;
    private String userName;

    public static ReservationPojo fromEntity(Reservation reservation) {
        ReservationPojo reservationPojo = new ReservationPojo();
        BeanUtils.copyProperties(reservation, reservationPojo);
        reservationPojo.setOfficeName(reservation.getOffice().getName());
        reservationPojo.setCabinet(reservation.getOffice().getCabinet());
        reservationPojo.setUserName(reservation.getUser().getName());
        return reservationPojo;
    }

    public Reservation toEntity(){
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(this, reservation);
        return reservation;
    }
}
