package com.ssau.reserv.service.implementation;

import com.ssau.reserv.dto.OfficePojo;
import com.ssau.reserv.entity.Office;
import com.ssau.reserv.exeption.OfficeAlreadyExistsException;
import com.ssau.reserv.exeption.OfficeNotFoundException;
import com.ssau.reserv.repository.OfficeRepository;
import com.ssau.reserv.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    @Override
    public OfficePojo add(OfficePojo officePojo) throws OfficeAlreadyExistsException {
        if(officeRepository.existsByName(officePojo.getName())&&officeRepository.existsByCabinet(officePojo.getCabinet()))
            throw new OfficeAlreadyExistsException("Office with name= " + officePojo.getName() + " already exists!" );
        Office office = officePojo.toEntity();
        return OfficePojo.fromEntity(officeRepository.save(office));
    }

    @Override
    public Optional<OfficePojo> getById(long id) {
        return officeRepository.findById(id).map(OfficePojo::fromEntity);
    }

    @Override
    public Optional<OfficePojo> getByName(String name) {
        return officeRepository.findGroupByName(name).map(OfficePojo::fromEntity);
    }

    @Override
    public OfficePojo update(OfficePojo officePojo) throws OfficeNotFoundException, OfficeAlreadyExistsException {
        if (!officeRepository.existsById(officePojo.getId()))
            throw new OfficeNotFoundException("Office with id= " + officePojo.getId() + " not found! ");
        return add(officePojo);
    }

    @Override
    public boolean deleteById(long id) {
        if (officeRepository.existsById(id)) {
            officeRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    @Override
    public long count() {
        return officeRepository.count();
    }

    @Override
    public List<OfficePojo> findAll() {
        return officeRepository.findAll()
                .stream()
                .map(OfficePojo::fromEntity)
                .toList();
    }
}
