package es.urjc.code.ejem0employee;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salaries")
@IdClass(SalaryId.class) // Para manejar la llave compuesta de MySQL
public class Salary {
    @Id
    @ManyToOne
    @JoinColumn(name = "emp_no")
    private Employee employee;

    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    private Integer salary;
    private LocalDate toDate;

    // Getters y Setters...
}

// Clase para la llave compuesta
class SalaryId implements Serializable {
    private Integer employee;
    private LocalDate fromDate;
}