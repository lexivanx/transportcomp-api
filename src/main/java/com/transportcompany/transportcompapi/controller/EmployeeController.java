package com.transportcompany.transportcompapi.controller;

import com.transportcompany.transportcompapi.model.Employee;
import com.transportcompany.transportcompapi.model.LicenseType;
import com.transportcompany.transportcompapi.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        if (employeeService.getEmployeeById(id).isPresent()) {
            return ResponseEntity.ok(employeeService.saveEmployee(employee));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        if (employeeService.getEmployeeById(id).isPresent()) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint for filtering employees by license, allowedSpecialCargo and salary fields
    @GetMapping("/filter")
    public ResponseEntity<List<Employee>> filterEmployees(
            @RequestParam(required = false) LicenseType license,
            @RequestParam(required = false) Boolean allowedSpecialCargo,
            @RequestParam(required = false) BigDecimal salary) {
        List<Employee> employees = employeeService.filterEmployees(license, allowedSpecialCargo, salary);
        return ResponseEntity.ok(employees);
    }

    // Endpoint for sorting employees by a field - license, allowedSpecialCargo or salary
    @GetMapping("/sort")
    public ResponseEntity<List<Employee>> sortEmployees(@RequestParam String sortBy) {
        List<Employee> employees = employeeService.sortEmployees(sortBy);
        return ResponseEntity.ok(employees);
    }

    // TODO Additional endpoints as required
}
