package es.urjc.code.ejem0employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Este Query causará un JOIN en SQL. 
    // Si no hay índices en la DB, Spring Boot tardará mucho en recibir la respuesta.
    @Query("SELECT e FROM Employee e JOIN e.salaries s WHERE s.salary > 80000")
    List<Employee> findHighEarners();

    @Query("SELECT DISTINCT e FROM Employee e JOIN e.salaries s WHERE s.salary > :minSalary")
    List<Employee> findEmployeesWithHighSalaries(@Param("minSalary") Integer minSalary);
}