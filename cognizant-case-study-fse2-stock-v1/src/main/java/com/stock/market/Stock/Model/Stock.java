package com.stock.market.Stock.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document("stock")
@Data
@ToString
@NoArgsConstructor
public class Stock
{
    @Id
    @JsonProperty("stockId")
    private int stockId;
    
    @JsonProperty("avgStockPrice")
    private float avgStockPrice;
    
    @JsonProperty("minStockPrice")
    private float minStockPrice;
    
    @JsonProperty("maxStockPrice")
    private float maxStockPrice;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    
    public LocalTime currentTime = LocalTime.now();
}
