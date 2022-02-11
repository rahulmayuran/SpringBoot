package com.stock.market.Company.Controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.market.Company.Model.Company;
import com.stock.market.Company.Service.CompanyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class CompanyController {

    @Autowired
    CompanyService companyService;
   

    @PostMapping("/company/register")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) throws JsonProcessingException 
    {
    	UUID randomUUID = UUID.randomUUID();

    	int maxId = companyService.getAllCompanies()
    			.stream()
                .max(Comparator.comparing(Company::getCompanyId))
                .get()
                .getCompanyId();
    	
    	if(maxId==0)
    	{ 	
    		company.setCompanyId(1);
        	company.setCompanyCode(randomUUID);   
        	Company addnewCompany = companyService.addCompany(company);
        	log.info("First company added -> "+addnewCompany);
            return new ResponseEntity<>(addnewCompany, HttpStatus.OK); 
    	}
    	else {
        	company.setCompanyId(maxId+1);
        	company.setCompanyCode(randomUUID);   
        	Company addotherCompany = companyService.addCompany(company);
        	log.info("Company added -> "+addotherCompany);
            return new ResponseEntity<>(addotherCompany, HttpStatus.OK);
    	}
    }
    
    @DeleteMapping("/company/delete/{id}")
    public String deleteCompany(@PathVariable int id) throws JsonProcessingException
    {
    	try {
			Optional<Company> deletedCompany = companyService.getCompanyById(id);
			companyService.deleteCompany(id);
			log.info("Company Deleted -> " + deletedCompany.get().getCompanyName());
			
		} catch (Exception e) {
			log.error("Some Exception occured while deleting " +e.getMessage());
		}
    	return "Company with id - "+id+" deleted";
    }

    @GetMapping("getCompany/{id}")
    public Optional<Company> getCompanyById(@PathVariable int id)
    {
    	Optional<Company> fetchCompanyById = companyService.getCompanyById(id);
    	log.info("Fetched company with id ("+id+") -> "+ fetchCompanyById );
        return fetchCompanyById;
    }

    @GetMapping("/getCompanies")
    public List<Company> getAllCompanies()
    {
        List<Company> companies = companyService.getAllCompanies();
        log.info("Fetched {"+ companies.stream().count() +
        		"} companies -> " +companies.stream().map(e->e.getCompanyName()).collect(Collectors.toList()) );
        return companies;
    }
}
