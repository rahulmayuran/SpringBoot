package com.stock.market.Stock.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stock.market.Stock.Model.Stock;
import com.stock.market.Stock.Service.StockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Tag(name = "stock", description = "The stocks API")
public class StockController {

    @Autowired
    StockService stockService;

    @Operation(summary = "Add a new stock", description = "", tags = { "stock" })
    @PostMapping(value = "/stock/register")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) 
    {
 	
    	UUID setUUID = UUID.randomUUID();
   
		stock.setStockId(setUUID.toString());
		stock.setStockDate(new Date());
		
    	Stock addOtherStock = stockService.addStock(stock);
        System.out.println("Company added "+addOtherStock);
        return new ResponseEntity<>(addOtherStock, HttpStatus.OK);
    	
    }

    @Operation(summary = "Deletes a Stock", description = "", tags = { "stock" })
    @DeleteMapping(value ="stock/delete/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable String id)
    {
    	if(id!=null)
    	{
    		stockService.deleteStock(id);
	        return new ResponseEntity<String>("Deleted Stock",HttpStatus.ACCEPTED);
    	}
    	return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND);
      
    }
    
    @Operation(summary = "Find stock by ID", description = "Returns a single stock", tags = { "stock" })
    @GetMapping(value = "getStock/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable String id)
    {
    		Optional<Stock> stock = stockService.getStockById(id);
			return ResponseEntity.of(stock);
    }
    
    @Operation(summary = "Fetch all stocks", description = "", tags = { "stock" })
    @GetMapping(value ="/getStocks")
    public ResponseEntity<List<Stock>> getAllStocks()
    {
        List<Stock> stocks = stockService.getAllStocks();
        if(stocks.size()==0) {
        	return new ResponseEntity<List<Stock>>(HttpStatus.NOT_FOUND);
        }
        stocks.forEach(System.out::println);
        return new ResponseEntity<List<Stock>>(stocks, HttpStatus.OK);
    }
    
    @GetMapping("/stock/search/{startDate}/{endDate}")
    public ResponseEntity<List<Stock>> dateBasedStocks(@PathVariable String startDate, @PathVariable String endDate)
    {
    	if(startDate==null||endDate == null||startDate==""||endDate=="") {
    		System.out.println("Dates are null");
    		return new ResponseEntity<List<Stock>>(HttpStatus.BAD_REQUEST);
    	}
    	try { 
    		stockService.getAllStocks()
    		.stream()
    		.forEach(e->
    		{
    			System.out.println("Stock Date for ("+e.getCompanyName()+" is "+e.getStockDate()+")");
    		});
    		
    		System.out.println("Path variables -> "+startDate+" to "+endDate);
    		
    		List<Stock> resultStock = stockService.dateFilteredStocks(startDate, endDate);
    		System.out.println("Spring :: found stocks from "+startDate+" to "+endDate+" are "+resultStock.size());
   
			return new ResponseEntity<List<Stock>>(resultStock, HttpStatus.OK);
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return new ResponseEntity<List<Stock>>(HttpStatus.BAD_GATEWAY);
	}
}
