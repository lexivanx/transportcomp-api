package com.transportcompany.transportcompapi.repository;

import com.transportcompany.transportcompapi.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
