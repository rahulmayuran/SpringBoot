package com.stock.market.Stock.Model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document("stock")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Stock
{
    @Id
    @JsonProperty("stockId")
    private String stockId;
    
    @JsonProperty("companyName")
    private String companyName;
    
    @JsonProperty("stockPrice")
    private float stockPrice;
    
    private Date stockDate;
    
}
 
