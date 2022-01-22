package com.stock.market.Stock.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.stock.market.Stock.Model.Stock;
import com.stock.market.Stock.Service.StockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
//@CrossOrigin
@Tag(name = "stock", description = "The stocks API")
public class StockController {

    @Autowired
    StockService stockService;

    @Operation(summary = "Add a new stock", description = "", tags = { "stock" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Stock created",
                content = @Content(schema = @Schema(implementation = Stock.class))), 
        @ApiResponse(responseCode = "400", description = "Invalid input"), 
        @ApiResponse(responseCode = "409", description = "Stock already exists") })
    @RequestMapping(method = RequestMethod.POST, value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE )
    public Stock addStock(@RequestBody Stock stock) {
        System.out.println("Added stock from Spring Boot - "+stock);
        return stockService.addStock(stock);
    }

    @Operation(summary = "Deletes a Stock", description = "", tags = { "stock" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "stock not found") })
    @RequestMapping(method = RequestMethod.DELETE, value ="/delete/{id}")
    public String deleteStock(@PathVariable int id){
        stockService.deleteStock(id);
        return "Stock with id - "+id+" deleted";
    }
    
    @Operation(summary = "Find stock by ID", description = "Returns a single stock", tags = { "stock" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
                content = @Content(schema = @Schema(implementation = Stock.class))),
        @ApiResponse(responseCode = "404", description = "Contact not found") })
    @RequestMapping(method = RequestMethod.GET, value = "getStock/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Stock> getStockById(@PathVariable int id){
        return stockService.getStockById(id);
    }
    
    @Operation(summary = "Fetch all stocks", description = "", tags = { "stock" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Stocks fetched"), 
        @ApiResponse(responseCode = "404", description = "Stocks not found") })
    @RequestMapping(method = RequestMethod.GET, value ="/getStocks", produces = "application/json")
    public List<Stock> getAllStocks()
    {
        List<Stock> stocks = stockService.getAllStocks();
        stocks.forEach(System.out::println);
        return stocks;
    }
}
