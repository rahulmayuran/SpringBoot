package com.stock.market.Company.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.market.Company.Model.Company;
import com.stock.market.Company.Service.CompanyService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class CompanyController {

    @Autowired
    CompanyService companyService;
    
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private KafkaTemplate<String, String> kakfaTemplate;

    public int returnMaxId() {
		List<Company> companyList = companyService.getAllCompanies();
		if (companyList.size() == 0) {
			return 0;
		}
		int maxId = companyService.getAllCompanies()
    			.stream()
                .max(Comparator.comparing(Company::getCompanyId))
                .get()
                .getCompanyId();		
		return maxId;
	}
    @PostMapping("/company/register")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) throws JsonProcessingException 
    {
    	UUID randomUUID = UUID.randomUUID();
    	
    	try {
			company.setCompanyId(returnMaxId() + 1);
        	company.setCompanyCode(randomUUID);   
			log.info("Save the company from Spring Boot - " + company);
			kakfaTemplate.send("fse_company", mapper.writeValueAsString("Save the company from Spring Boot - " + company));
			companyService.addCompany(company);
		} catch (Exception e) {
			log.error("Exception while saving company, "+e);
            kakfaTemplate.send("fse_company", "Unable to save company, "+e.getMessage());
        }
        return new ResponseEntity<>(company, HttpStatus.OK); 
		
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
        Optional<Company> companyFetchedById = companyService.getCompanyById(id);
        kakfaTemplate.send("fse_company", "Fetched Company with id, "+id+" -> "+companyFetchedById);
        return companyFetchedById;
    }

    @GetMapping("/getCompanies")
    public List<Company> getAllCompanies() throws JsonProcessingException
    {
        List<Company> companies = companyService.getAllCompanies();
        log.info("Total Companies, "+companies.stream().count() +
        		", names -> " + companies.stream().map(s->s.getCompanyName()).collect(Collectors.toList()));
        kakfaTemplate.send("fse_company", mapper.writeValueAsString("Total Companies, "+companies.stream().count() +
                ", names -> " + companies.stream().map(s->s.getCompanyName()).collect(Collectors.toList())));
        return companies;
    }
}
