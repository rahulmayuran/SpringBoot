package com.stock.market.Stock.Controller;

import com.stock.market.Stock.Model.Stock;
import com.stock.market.Stock.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class StockController {

    @Autowired
    StockService stockService;

    @PostMapping("/register")
    public Stock addStock(@RequestBody Stock stock) {
        System.out.println("Added stock from Spring Boot - "+stock);
        return stockService.addStock(stock);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStock(@PathVariable int id){
        stockService.deleteStock(id);
        return "Stock with id - "+id+" deleted";
    }

    @GetMapping("getStock/{id}")
    public Optional<Stock> getStockById(@PathVariable int id){
        return stockService.getStockById(id);
    }

    @GetMapping("/getStocks")
    public List<Stock> getAllStocks()
    {
        List<Stock> stocks = stockService.getAllStocks();
        stocks.forEach(System.out::println);
        return stocks;
    }
}
