package com.stock.market.Company.Model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document("company")
@Data
@ToString
@NoArgsConstructor
public class Company
{
    @Id
    @JsonProperty("companyId")
    private int companyId;
    
    @JsonProperty("companyCode")
    private UUID companyCode;
    
    @JsonProperty("companyName")
    private String companyName;
    
    @JsonProperty("companyCEO")
    private String companyCEO;
    
    @JsonProperty("companyWebsite")
    private String companyWebsite;
    
    @JsonProperty("companyTurnover")
    private String companyTurnover;
    
    @JsonProperty("isNSE")
    private boolean isNSE;
    
    @JsonProperty("isBSE")
    private boolean isBSE;
}
