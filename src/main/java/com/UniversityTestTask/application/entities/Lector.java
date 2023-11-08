package com.UniversityTestTask.application.entities;

import com.UniversityTestTask.application.enums.Degree;
import com.UniversityTestTask.application.interfaces.Employee;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
public class Lector implements Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Degree degree;

    private Integer salary;

    @ManyToMany
    private Set<Department> departments = new HashSet<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public Degree getDegree() {
        return degree;
    }

    @Override
    public Integer getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
