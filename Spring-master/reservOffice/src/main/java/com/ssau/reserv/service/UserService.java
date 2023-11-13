package com.ssau.reserv.service;

import com.ssau.reserv.dto.UserPojo;
import com.ssau.reserv.exeption.OfficeAlreadyExistsException;

import java.util.List;

public interface UserService {
    UserPojo add(UserPojo userPojo) throws OfficeAlreadyExistsException;

    List<UserPojo> findAll();
}
