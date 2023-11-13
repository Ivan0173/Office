package com.ssau.reserv.dto;

import com.ssau.reserv.entity.User;
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
public class UserPojo {
    private long id;
    @NotBlank
    private String name;
    private String nameOffice;
    private String email;
    private String phone;
    private List<ReservationPojo> reservation = new LinkedList<>();

    public static UserPojo fromEntity(User office) {
        UserPojo officePojo = new UserPojo();
        BeanUtils.copyProperties(office, officePojo);
        officePojo.setReservation(
                office.getReservations()
                        .stream()
                        .map(ReservationPojo::fromEntity)
                        .toList()
        );
        return officePojo;
    }

    public User toEntity() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}