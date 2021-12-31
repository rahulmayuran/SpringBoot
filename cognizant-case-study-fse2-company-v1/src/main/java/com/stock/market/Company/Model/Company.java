package com.stock.market.Company.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Document("company")
public class Company
{
    @Id
    private int companyId;
    private UUID companyCode;
    private String companyName;
    private String companyCEO;
    private String companyWebsite;
    private String companyTurnover;
    private String stockExchangeType;

    public Company(){

    }

    public Company(int companyId, UUID companyCode, String companyName, String companyCEO, String companyWebsite, String companyTurnover, String stockExchangeType) {
        this.companyId = companyId;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyCEO = companyCEO;
        this.companyWebsite = companyWebsite;
        this.companyTurnover = companyTurnover;
        this.stockExchangeType = stockExchangeType;
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

    public String getStockExchangeType() {
        return stockExchangeType;
    }

    public void setStockExchangeType(String stockExchangeType) {
        this.stockExchangeType = stockExchangeType;
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
                ", stockExchangeType=" + stockExchangeType +
                '}';
    }
}
