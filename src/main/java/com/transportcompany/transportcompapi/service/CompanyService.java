package com.transportcompany.transportcompapi.service;

import com.transportcompany.transportcompapi.model.Company;
import com.transportcompany.transportcompapi.repository.CompanyRepository;
import com.transportcompany.transportcompapi.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ShipmentRepository shipmentRepository;

    public CompanyService(CompanyRepository companyRepository, ShipmentRepository shipmentRepository) {
        this.companyRepository = companyRepository;
        this.shipmentRepository = shipmentRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(int id) {
        return companyRepository.findById(id);
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }

    // Method to sort companies by revenue, sorted via sequential stream
    public List<Map<String, Object>> getCompaniesSortedByRevenue() {
        return shipmentRepository.findCompaniesAndRevenue()
                .stream()
                .sorted((e1, e2) -> ((BigDecimal)e2.get("revenue")).compareTo((BigDecimal)e1.get("revenue")))
                .collect(Collectors.toList());
    }
}
