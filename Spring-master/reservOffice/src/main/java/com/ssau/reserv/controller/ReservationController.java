package com.ssau.reserv.controller;

import com.ssau.reserv.dto.ReservationPojo;
import com.ssau.reserv.exeption.OfficeNotFoundException;
import com.ssau.reserv.exeption.ReservationNotFoundException;
import com.ssau.reserv.service.ReservationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody ReservationPojo reservationPojo) {
        try{
            return ResponseEntity.ok(reservationService.add(reservationPojo));
        }catch (OfficeNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@NotNull @PathVariable Long id){
        return ResponseEntity.ok(reservationService.deleteById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody ReservationPojo reservationPojo) {
        try {
            return ResponseEntity.ok(reservationService.update(reservationPojo));
        }
        catch (ReservationNotFoundException | OfficeNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ReservationPojo> getById(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getById(id).orElse(null));
    }


    @GetMapping("/count")
    public Long count() {
        return reservationService.count();
    }

   @GetMapping()
    public List<ReservationPojo> findAll() {
        return reservationService.findAll();
    }

/*    @GetMapping("/{name}")
    public List<ReservationPojo> findAllByName(@NotBlank @PathVariable String name) {
        return reservationService.findAllByName(name);
    }*/
}
