package es.urjc.code.ejem0employee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeRepository repo;
    
    public EmployeeController(EmployeeRepository repository) {
        this.repo = repository;
    }

    @GetMapping("slow-join")
    public Map<String, Object> slowJoin(@RequestParam(defaultValue = "80000") Integer minSalary) {
        List<Employee> employees = repo.findEmployeesWithHighSalaries(minSalary);
        
        return Map.of(
            "status", "success",
            "recordsFound", employees.size()
        );
    }

    @GetMapping("fast/{id}")
    public Map<String, Object> fast(@PathVariable Integer id) {

        return Map.of("rapido", "muy rápido");
        // Optional<Employee> emp = repo.findById(id);

        // return Map.of(
        //     "found", emp.isPresent()
        // );

    }
    
    
}
