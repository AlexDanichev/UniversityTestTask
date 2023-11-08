package com.UniversityTestTask.application.repositories;

import com.UniversityTestTask.application.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Custom query methods can be defined here
    Department findByName(String name);

    @Query("SELECT d FROM Department d WHERE LOWER(d.name) = LOWER(:name)")
    Department findByNameIgnoreCase(@Param("name") String name);

    @Query("SELECT d FROM Department d WHERE lower(d.name) LIKE lower(concat('%', :template, '%'))")
    List<Department> searchDepartmentsByTemplate(@Param("template") String template);
}