package com.stock.market.Stock.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document("stock")
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
