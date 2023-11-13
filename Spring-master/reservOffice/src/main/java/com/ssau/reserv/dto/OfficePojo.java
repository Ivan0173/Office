package com.ssau.reserv.dto;

import com.ssau.reserv.entity.Office;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
public class OfficePojo {
    private long id;
    @NotBlank
    private String name;
   // @NotBlank
    private String cabinet;
    private boolean projector;
    private boolean board;
    private int numberSeats;
    private List<ReservationPojo> reservation = new LinkedList<>();

    public static OfficePojo fromEntity(Office office) {
        OfficePojo officePojo = new OfficePojo();
        BeanUtils.copyProperties(office, officePojo);
        officePojo.setReservation(
                office.getReservations()
                        .stream()
                        .map(ReservationPojo::fromEntity)
                        .toList()
        );
        return officePojo;
    }

    public Office toEntity() {
        Office office = new Office();
        BeanUtils.copyProperties(this, office);
        return office;
    }
}
