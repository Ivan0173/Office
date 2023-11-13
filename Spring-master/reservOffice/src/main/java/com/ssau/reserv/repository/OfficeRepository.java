package com.ssau.reserv.repository;

import com.ssau.reserv.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office, Long> {

    Optional<Office> findGroupByName(String name);

    @Override
    List<Office> findAll();

    boolean existsByName(String name);

    boolean existsByCabinet(String name);
}
