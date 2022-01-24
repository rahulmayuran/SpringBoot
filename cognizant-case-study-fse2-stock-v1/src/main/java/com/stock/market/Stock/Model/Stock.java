package com.stock.market.Stock.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("stock")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Stock
{
    @Id
    @JsonProperty("stockId")
    @Schema(description = "Unique identifier of the Contact.", 
    example = "1", required = false)
    private String stockId;
    
    @JsonProperty("companyName")
    private String companyName;
    
    @JsonProperty("stockPrice")
    @Schema(description = "Average price of stock.", 
    example = "40.34", required = false)
    private float stockPrice;
    
    @Schema(description = "Stock Date.", 
    example = "2022-01-11", required = false)
    private Date stockDate;
    
}
 
