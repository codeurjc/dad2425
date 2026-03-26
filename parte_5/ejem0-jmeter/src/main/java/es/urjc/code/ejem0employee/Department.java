package es.urjc.code.ejem0employee;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "dept_no")
    private String id;

    @Column(name = "dept_name", unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
        name = "dept_emp",
        joinColumns = @JoinColumn(name = "dept_no"),
        inverseJoinColumns = @JoinColumn(name = "emp_no")
    )
    private List<Employee> employees;
}