package es.urjc.code.ejem0employee;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "emp_no")
    private Integer id;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('M', 'F')")
    private Gender gender;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    // Relación 1:N con Salarios
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Salary> salaries;

    // Getters y Setters...
}

enum Gender { M, F }