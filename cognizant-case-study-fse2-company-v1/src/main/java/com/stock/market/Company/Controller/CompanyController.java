package com.stock.market.Company.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.market.Company.Model.Company;
import com.stock.market.Company.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CompanyController {

    @Autowired
    CompanyService companyService;
    
    @Autowired
    private ObjectMapper mapper;
    
    @Autowired
    private KafkaTemplate<String, String> kakfaTemplate;

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
            System.out.println("Company added "+ mapper.writeValueAsString(addnewCompany));
            kakfaTemplate.send("fse_company", "***Adding First Company***");
            kakfaTemplate.send("fse_company", mapper.writeValueAsString(addnewCompany));
            return new ResponseEntity<>(addnewCompany, HttpStatus.OK); 
    	}
    	else {
        	company.setCompanyId(maxId+1);
        	company.setCompanyCode(randomUUID);   
        	Company addotherCompany = companyService.addCompany(company);
        	System.out.println("Company added "+ mapper.writeValueAsString(addotherCompany));
        	kakfaTemplate.send("fse_company", "***Adding Company*** -> "+company.getCompanyName());
            kakfaTemplate.send("fse_company", mapper.writeValueAsString(addotherCompany));
            return new ResponseEntity<>(addotherCompany, HttpStatus.OK);
    	}
    }
    
    @DeleteMapping("/company/delete/{id}")
    public String deleteCompany(@PathVariable int id) throws JsonProcessingException{
        companyService.deleteCompany(id);
        kakfaTemplate.send("fse_company", "***Deleted Company***");
        kakfaTemplate.send("fse_company", mapper.writeValueAsString(id));
        return "Company with id - "+id+" deleted";
    }

    @GetMapping("getCompany/{id}")
    public Optional<Company> getCompanyById(@PathVariable int id){
        return companyService.getCompanyById(id);
    }

    @GetMapping("/getCompanies")
    public List<Company> getAllCompanies()
    {
        List<Company> companies = companyService.getAllCompanies();
        companies.forEach(System.out::println);
        return companies;
    }
}
