package com.stock.market.Stock.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.kafka.common.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.stock.market.Stock.Model.Stock;
import com.stock.market.Stock.Repository.StockRepository;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;
    
//    @Autowired
//    KafkaTemplate<String, String> kafkaTemplate;
    
    @Bean
    public void TestingKafkaMessages() {
//    	kafkaTemplate.send("fse_stock", "Inside Stock Service ~ " + Uuid.randomUuid());
    }

    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public void deleteStock(String id){
        System.out.println("Stock with id - "+id+" deleted");
        stockRepository.deleteById(id);
        return;
    }

    public Optional<Stock> getStockById(String id){
        return stockRepository.findById(id);
    }

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

    
    public List<Stock> dateFilteredStocks(String fromDate, String toDate)
    {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:s");
    	Date startDate = new Date();
    	Date endDate = new Date();
    	
    	if(fromDate==null||toDate == null) {
    		System.out.println("Dates are null");
    		return new ArrayList<>();
    	}
    	try 
    	{
    		startDate = simpleDateFormat.parse(fromDate+" 00:00:00");
    		endDate = simpleDateFormat.parse(toDate +" 23:59:59");
    		System.out.println("Parsed Dates {"+startDate+"}-{"+endDate+"}");
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
		return stockRepository.getStocksBasedOnDates(startDate, endDate);	      	
    }


}
