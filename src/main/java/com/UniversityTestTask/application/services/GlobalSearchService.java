package com.UniversityTestTask.application.services;

import com.UniversityTestTask.application.entities.Department;
import com.UniversityTestTask.application.entities.Lector;
import com.UniversityTestTask.application.repositories.LectorRepository;
import com.UniversityTestTask.application.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GlobalSearchService {

    private final LectorRepository lectorRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public GlobalSearchService(LectorRepository lectorRepository, DepartmentRepository departmentRepository) {
        this.lectorRepository = lectorRepository;
        this.departmentRepository = departmentRepository;
    }


    public List<Lector> searchLectors(String template) {
        return lectorRepository.searchLectorsByTemplate(template);
    }

    public List<Department> searchDepartments(String template) {
        return departmentRepository.searchDepartmentsByTemplate(template);
    }
}
