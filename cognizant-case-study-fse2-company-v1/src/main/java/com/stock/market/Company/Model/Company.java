package com.stock.market.Company.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Document("company")
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

    public Company(){

    }

    public Company(int companyId, UUID companyCode, String companyName, String companyCEO, String companyWebsite, String companyTurnover, boolean isNSE, boolean isBSE) {
        this.companyId = companyId;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyCEO = companyCEO;
        this.companyWebsite = companyWebsite;
        this.companyTurnover = companyTurnover;
        this.isNSE = isNSE;
        this.isBSE = isBSE;

    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public UUID getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(UUID companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCEO() {
        return companyCEO;
    }

    public void setCompanyCEO(String companyCEO) {
        this.companyCEO = companyCEO;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyTurnover() {
        return companyTurnover;
    }

    public void setCompanyTurnover(String companyTurnover) {
        this.companyTurnover = companyTurnover;
    }

    public boolean isNSE() {
		return isNSE;
	}

	public void setNSE(boolean isNSE) {
		this.isNSE = isNSE;
	}

	public boolean isBSE() {
		return isBSE;
	}

	public void setBSE(boolean isBSE) {
		this.isBSE = isBSE;
	}

	@Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyCode=" + companyCode +
                ", companyName='" + companyName + '\'' +
                ", companyCEO='" + companyCEO + '\'' +
                ", companyWebsite='" + companyWebsite + '\'' +
                ", companyTurnover=" + companyTurnover +
                ", isNSE=" + isNSE +
                ", isBSE=" + isBSE +
                '}';
    }
}
