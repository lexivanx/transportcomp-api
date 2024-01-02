package com.transportcompany.transportcompapi.service;

import com.transportcompany.transportcompapi.model.Employee;
import com.transportcompany.transportcompapi.model.LicenseType;
import com.transportcompany.transportcompapi.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    // Method for filtering employees by criteria
    public List<Employee> filterEmployees(LicenseType license, Boolean allowedSpecialCargo, BigDecimal salary) {
        return employeeRepository.findByLicenseAndAllowedSpecialCargoAndSalary(license, allowedSpecialCargo, salary);
    }

    // Method for sorting employees by criteria
    public List<Employee> sortEmployees(String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        return employeeRepository.findAll(sort);
    }
}

