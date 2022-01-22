package com.stock.market.Stock.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document("stock")
public class Stock
{
    @Id
    @JsonProperty("stockId")
    @Schema(description = "Unique identifier of the Contact.", 
    example = "1", required = false)
    private int stockId;
    
    @JsonProperty("avgStockPrice")
    @Schema(description = "Average price of stock.", 
    example = "40.34", required = false)
    private float avgStockPrice;
    
    @JsonProperty("minStockPrice")
    @Schema(description = "Minimum price of stock.", 
    example = "32.34", required = false)
    private float minStockPrice;
    
    @JsonProperty("maxStockPrice")
    @Schema(description = "Maximum price of stock.", 
    example = "45.34", required = false)
    private float maxStockPrice;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(description = "Start Date.", 
    example = "2022-01-11", required = false)
    private LocalDate startDate;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(description = "End Date.", 
    example = "2022-01-11", required = false)
    private LocalDate endDate;
    
    @Schema(description = "Time added.", 
    	    example = "12:56 pm", required = false)
    public LocalTime currentTime = LocalTime.now();

    public Stock(){
    }

    public Stock(int stockId, float avgStockPrice, float minStockPrice, float maxStockPrice, LocalDate startDate, LocalDate endDate, LocalTime currentTime) {
        this.stockId = stockId;
        this.avgStockPrice = avgStockPrice;
        this.minStockPrice = minStockPrice;
        this.maxStockPrice = maxStockPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentTime = currentTime;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public float getAvgStockPrice() {
        return avgStockPrice;
    }

    public void setAvgStockPrice(float avgStockPrice) {
        this.avgStockPrice = avgStockPrice;
    }

    public float getMinStockPrice() {
        return minStockPrice;
    }

    public void setMinStockPrice(float minStockPrice) {
        this.minStockPrice = minStockPrice;
    }

    public float getMaxStockPrice() {
        return maxStockPrice;
    }

    public void setMaxStockPrice(float maxStockPrice) {
        this.maxStockPrice = maxStockPrice;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", avgStockPrice=" + avgStockPrice +
                ", minStockPrice=" + minStockPrice +
                ", maxStockPrice=" + maxStockPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currentTime=" + currentTime +
                '}';
    }
}
