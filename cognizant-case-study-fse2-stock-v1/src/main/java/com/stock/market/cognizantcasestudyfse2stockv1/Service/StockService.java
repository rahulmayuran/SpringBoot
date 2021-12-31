package com.stock.market.cognizantcasestudyfse2stockv1.Service;

import com.stock.market.cognizantcasestudyfse2stockv1.Model.Stock;
import com.stock.market.cognizantcasestudyfse2stockv1.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public void deleteStock(int id){
        System.out.println("Stock with id - "+id+" deleted");
        stockRepository.deleteById(id);
        return;
    }

    public Optional<Stock> getStockById(int id){
        return stockRepository.findById(id);
    }

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }


}
