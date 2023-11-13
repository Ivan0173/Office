package com.ssau.reserv.service.implementation;

import com.ssau.reserv.dto.UserPojo;
import com.ssau.reserv.entity.User;
import com.ssau.reserv.exeption.OfficeAlreadyExistsException;
import com.ssau.reserv.repository.UserRepository;
import com.ssau.reserv.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserPojo add(UserPojo userPojo)throws OfficeAlreadyExistsException {
        if(userRepository.existsByName(userPojo.getName()))
            throw new OfficeAlreadyExistsException("Пользователь с имянем= " + userPojo.getName() + " уже существует!" ); {
        User user = userPojo.toEntity();
        return UserPojo.fromEntity(userRepository.save(user));
        }
    }
    @Override
    public List<UserPojo> findAll(){
        return userRepository.findAll()
                .stream()
                .map(UserPojo::fromEntity)
                .toList();
    }
}
