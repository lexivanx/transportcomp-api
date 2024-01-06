package com.transportcompany.transportcompapi.controller;

import com.transportcompany.transportcompapi.model.Employee;
import com.transportcompany.transportcompapi.model.LicenseType;
import com.transportcompany.transportcompapi.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
        return employeeService.getEmployeeById(id)
                .map(existingEmployee -> {
                    employee.setEmployeeID(id);
                    return ResponseEntity.ok(employeeService.saveEmployee(employee));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
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

    // Endpoints for filtering employees by license, allowedSpecialCargo and salary fields
    @GetMapping("/filter/license")
    public ResponseEntity<List<Employee>> filterEmployeesByLicense(@RequestParam LicenseType license) {
        List<Employee> employees = employeeService.filterEmployeesByLicense(license);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/filter/special-cargo")
    public ResponseEntity<List<Employee>> filterEmployeesByAllowedSpecialCargo(@RequestParam Boolean allowedSpecialCargo) {
        List<Employee> employees = employeeService.filterEmployeesByAllowedSpecialCargo(allowedSpecialCargo);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/filter/salary")
    public ResponseEntity<List<Employee>> filterEmployeesBySalary(@RequestParam BigDecimal salary) {
        List<Employee> employees = employeeService.filterEmployeesBySalary(salary);
        return ResponseEntity.ok(employees);
    }


    // Endpoint for sorting employees by a field - license, allowedSpecialCargo or salary
    @GetMapping("/sort")
    public ResponseEntity<List<Employee>> sortEmployees(@RequestParam String sortBy) {
        List<Employee> employees = employeeService.sortEmployees(sortBy);
        return ResponseEntity.ok(employees);
    }

    // Endpoint to get all drivers with their shipment amount
    @GetMapping("/drivers/shipment-count")
    public ResponseEntity<List<Map<String, Object>>> getAllDriversWithShipmentCount() {
        List<Map<String, Object>> drivers = employeeService.getAllDriversWithShipmentCount();
        return ResponseEntity.ok(drivers);
    }

    // Endpoint to get all drivers with each revenue
    @GetMapping("/drivers/total-revenue")
    public ResponseEntity<List<Map<String, Object>>> getAllDriversWithTotalRevenue() {
        List<Map<String, Object>> drivers = employeeService.getAllDriversWithTotalRevenue();
        return ResponseEntity.ok(drivers);
    }
}
