package com.ssau.reserv.controller;

import com.ssau.reserv.dto.UserPojo;
import com.ssau.reserv.exeption.OfficeAlreadyExistsException;
import com.ssau.reserv.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody UserPojo userPojo) {
        try{
            return ResponseEntity.ok(userService.add(userPojo));
        }catch (OfficeAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity<List<UserPojo>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
