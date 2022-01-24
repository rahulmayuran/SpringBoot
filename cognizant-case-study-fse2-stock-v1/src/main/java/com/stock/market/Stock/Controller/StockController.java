package com.stock.market.Stock.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stock.market.Stock.Model.Stock;
import com.stock.market.Stock.Service.StockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
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
    @RequestMapping(method = RequestMethod.POST, value = "/stock/register", consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) 
    {
        System.out.println("Added stock from Spring Boot - "+stock);
 	
    	if(stockService.getAllStocks().size() == 0)
    	{ 	
    		stock.setStockId(1);
    		stock.setStockDate(new Date());
    		
        	Stock freshStock = stockService.addStock(stock);
            System.out.println("Company added "+freshStock);
            return new ResponseEntity<>(freshStock, HttpStatus.OK); 
    	}
    	else {
    		
    		 int maxId = stockService.getAllStocks()
    	    			.stream()
    	                .max(Comparator.comparing(Stock::getStockId))
    	                .get()
    	                .getStockId();
    		  
    		stock.setStockId(maxId+1);
    		stock.setStockDate(new Date());
    		
        	Stock addOtherStock = stockService.addStock(stock);
            System.out.println("Company added "+addOtherStock);
            return new ResponseEntity<>(addOtherStock, HttpStatus.OK);
    	}
    }

    @Operation(summary = "Deletes a Stock", description = "", tags = { "stock" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "404", description = "stock not found") })
    @RequestMapping(method = RequestMethod.DELETE, value ="stock/delete/{id}")
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
    
    @GetMapping("/stock/search/{startDate}/{endDate}")
    public List<Stock> dateBasedStocks(
    		@PathVariable String startDate,
    		@PathVariable String endDate
    		){
    	try { 
    		stockService.getAllStocks()
    		.stream()
    		.forEach(e->
    		{
    			System.out.println("Start("+e.getStockDate()+")");
    		});
    		
    		System.out.println("Path variables -> "+startDate+" to "+endDate);
    		
    		List<Stock> resultStock = stockService.dateFilteredStocks(startDate, endDate);
    		System.out.println("Spring :: Filtered stocks from "+startDate+" to "+endDate+" are "+resultStock);
   
			return resultStock;
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return new ArrayList<>();
	}
}
