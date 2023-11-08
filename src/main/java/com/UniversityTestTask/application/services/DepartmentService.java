package com.UniversityTestTask.application.services;

import com.UniversityTestTask.application.entities.Department;
import com.UniversityTestTask.application.entities.Lector;
import com.UniversityTestTask.application.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }

    public Department findByNameIgnoreCase(String name) {
        return departmentRepository.findByNameIgnoreCase(name);

    }

    public Double findAverageSalaryByDepartmentName(String name) {
        Department department = departmentRepository.findByNameIgnoreCase(name);
        if (department != null) {
            return department.getLectors()
                    .stream()
                    .mapToInt(Lector::getSalary)
                    .average().getAsDouble();
        }
        return null;
    }

    public Long countEmployeesByDepartmentName(String name) {
        Department department = departmentRepository.findByNameIgnoreCase(name);
        if (department != null) {
            return (long) department.getLectors().size();
        }
        return null;
    }
}