package com.UniversityTestTask.application.shell;
import com.UniversityTestTask.application.entities.Lector;
import com.UniversityTestTask.application.entities.Department;
import com.UniversityTestTask.application.enums.Degree;
import com.UniversityTestTask.application.services.DepartmentService;
import com.UniversityTestTask.application.services.GlobalSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ShellComponent
public class UniversityShellComponent {

    private final DepartmentService departmentService;
    private final GlobalSearchService globalSearchService;

    @Autowired
    public UniversityShellComponent(DepartmentService departmentService, GlobalSearchService globalSearchService) {
        this.departmentService = departmentService;
        this.globalSearchService = globalSearchService;
    }

    @ShellMethod(value = "Finds the head of the specified department." +
            " Department name which consist from two or more words must be written in quotes", key = "Who is head of department")

    public String whoIsHeadOfDepartment(@ShellOption(arity = -1) String[] args) {
        System.out.println("Received args: " + Arrays.toString(args)); // Add this line for debugging
        String departmentName = String.join(" ", args);

        Department department = departmentService.findByNameIgnoreCase(departmentName);
        if (department != null && department.getHead() != null) {
            return String.format("Head of %s department is %s %s",
                    department.getName(), department.getHead().getName(), department.getHead().getSurname());
        } else {
            return String.format("Department \"%s\" not found or has no head.", departmentName);
        }
    }

    @ShellMethod(value = "Shows statistics for the specified department."+
            " Department name which consist from two or more words must be written in quotes", key = "Show statistics")
    public String showDepartmentStatistics(@ShellOption String[] args) {
        String departmentName = String.join(" ", args);
        Department department = departmentService.findByNameIgnoreCase(departmentName);

        if (department == null) {
            return String.format("Department \"%s\" not found.", departmentName);
        }

        long assistantsCount = department.getLectors().stream()
                .filter(lector -> lector.getDegree() == Degree.ASSISTANT)
                .count();
        long associateProfessorsCount = department.getLectors().stream()
                .filter(lector -> lector.getDegree() == Degree.ASSOCIATE_PROFESSOR)
                .count();
        long professorsCount = department.getLectors().stream()
                .filter(lector -> lector.getDegree() == Degree.PROFESSOR)
                .count();

        return String.format("assistants - %d. associate professors - %d. professors -%d",
                assistantsCount, associateProfessorsCount, professorsCount);
    }

    @ShellMethod(value = "Shows the average salary for the specified department."+
            " Department name which consist from two or more words must be written in quotes", key = "Show the average salary for the department")
    public String showAverageSalary(@ShellOption String[] args) {
        String departmentName = String.join(" ", args);
        Double averageSalary = departmentService.findAverageSalaryByDepartmentName(departmentName);

        if (averageSalary != null) {
            return String.format("The average salary of %s department is %.2f", departmentName, averageSalary);
        } else {
            return String.format("Department \"%s\" not found or has no lecturers.", departmentName);
        }
    }

    @ShellMethod(value = "Shows the count of employees for the specified department."+
            " Department name which consist from two or more words must be written in quotes", key = "Show count of employee for")
    public String showEmployeeCount(@ShellOption String[] args) {
        String departmentName = String.join(" ", args);
        Long employeeCount = departmentService.countEmployeesByDepartmentName(departmentName);

        if (employeeCount != null) {
            return String.format("%d", employeeCount);
        } else {
            return String.format("Department \"%s\" not found.", departmentName);
        }
    }

    @ShellMethod(value = "Performs a global search by the given template.", key = "Global search by")
    public String globalSearch(@ShellOption String template) {
        List<Lector> lectors = globalSearchService.searchLectors(template);
        List<Department> departments = globalSearchService.searchDepartments(template);

        List<String> results = new ArrayList<>();

        lectors.stream()
                .map(lector -> lector.getName() + " " + lector.getSurname())
                .forEach(results::add);

        departments.stream()
                .map(Department::getName)
                .forEach(results::add);

        if (results.isEmpty()) {
            return "No results found for '" + template + "'";
        }

        return String.join(", ", results);
    }
}

