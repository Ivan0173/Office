package com.ssau.reserv.controller;

import com.ssau.reserv.dto.OfficePojo;
import com.ssau.reserv.exeption.OfficeAlreadyExistsException;
import com.ssau.reserv.exeption.OfficeNotFoundException;
import com.ssau.reserv.exeption.ReservationNotFoundException;
import com.ssau.reserv.service.OfficeService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/offices")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody OfficePojo officePojo) {
        try {
            return ResponseEntity.ok(officeService.add(officePojo));
        }catch (OfficeAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(officeService.deleteById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody OfficePojo officePojo) throws OfficeNotFoundException {
        try {
            return ResponseEntity.ok(officeService.update(officePojo));
        }catch (OfficeNotFoundException | ReservationNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (OfficeAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(e.getMessage());
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<OfficePojo> getById(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(officeService.getById(id).orElse(null));
    }

    @GetMapping("/{name}")
    public ResponseEntity<OfficePojo> getByName(@NotBlank @PathVariable String name) {
        return ResponseEntity.ok(officeService.getByName(name).orElse(null));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(officeService.count());
    }

    @GetMapping()
    public ResponseEntity<List<OfficePojo>> findAll() {
        return ResponseEntity.ok(officeService.findAll());
    }


}
