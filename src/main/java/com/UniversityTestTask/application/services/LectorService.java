package com.UniversityTestTask.application.services;

import com.UniversityTestTask.application.entities.Lector;
import com.UniversityTestTask.application.repositories.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LectorService {

    private final LectorRepository lectorRepository;

    @Autowired
    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public Optional<Lector> findById(Long id) {
        return lectorRepository.findById(id);
    }

    // Additional business logic and utility methods can be added here
}


