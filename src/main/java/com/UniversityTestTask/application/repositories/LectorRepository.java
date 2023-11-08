package com.UniversityTestTask.application.repositories;


import com.UniversityTestTask.application.entities.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Query("SELECT l FROM Lector l WHERE lower(l.name) LIKE lower(concat('%', :template, '%')) OR lower(l.surname) LIKE lower(concat('%', :template, '%'))")
    List<Lector> searchLectorsByTemplate(@Param("template") String template);
}
