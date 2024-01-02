package com.transportcompany.transportcompapi.controller;

import com.transportcompany.transportcompapi.model.Company;
import com.transportcompany.transportcompapi.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id, @RequestBody Company company) {
        if (companyService.getCompanyById(id).isPresent()) {
            return ResponseEntity.ok(companyService.saveCompany(company));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable int id) {
        if (companyService.getCompanyById(id).isPresent()) {
            companyService.deleteCompany(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to sort companies by revenue
    @GetMapping("/sorted-by-revenue")
    public ResponseEntity<List<Map<String, Object>>> getCompaniesSortedByRevenue() {
        List<Map<String, Object>> companies = companyService.getCompaniesSortedByRevenue();
        return ResponseEntity.ok(companies);
    }
    // TODO Additional endpoints as required
}
